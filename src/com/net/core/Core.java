package com.net.core;

import com.net.core.Tools.ResponseHandler;
import com.net.core.Tools.configuration.AppConf;
import com.net.core.Tools.configuration.AppConfiguration;
import com.net.core.Tools.configuration.BaseConfiguration;
import com.net.core.Tools.configuration.ServerConfiguration;
import com.net.core.Tools.App;
import com.net.core.exception.AppLoadException;
import com.net.core.exception.ConfigurationLoadException;
import com.net.core.exception.RequestException;
import com.net.core.http_module.Request;
import com.net.core.http_module.Response;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuxiuyin on 16-5-24.
 */
public class Core
{
    private ServerConfiguration configuration = null;
    private ServerSocket serverSocket = null;
    private Map<String, App> hostMap = new HashMap<>();
    private App defaultApp = null;
    private Response defaultResponse = null;
    private ResponseHandler responseHandler = null;

    public Core()
    {
        System.out.println("core loading");
        System.out.println("begin to load conf");
        try
        {
            configuration = (ServerConfiguration)BaseConfiguration.load("./server.json");
        } catch (ConfigurationLoadException e)
        {
            e.printStackTrace();
            System.out.println("core load failed");
            System.exit(-1);
        }
        System.out.println("conf load success");

        System.out.println("begin to load app");
        for (AppConf conf : configuration.getAllApp())
        {
            try
            {
                App app = new App(conf);
                if (conf.getHost().equals("*"))
                {
                    if (defaultApp != null)
                    {
                        System.err.println(String.format("warning: app: %s 替换掉了 app: %s 对Path'*'的执行权",
                                conf.getPath(), defaultApp.getAppPath()));
                    }
                    defaultApp = app;
                }
                else
                {
                    hostMap.put(conf.getHost(), app);
                }
                System.out.println(String.format("加载app: %s 用于处理 Path: %s", conf.getPath(), conf.getHost()));
            }
            catch (AppLoadException e)
            {
                e.printStackTrace();
                System.out.println("app: " + conf.getPath() + "加载失败");
            }
        }
        System.out.println("app load end");

        System.out.println("begin to open socket");
        System.out.println("在" + configuration.getServerPort() + "打开ServerSocket");
        try
        {
            serverSocket = new ServerSocket(configuration.getServerPort(), 1);
        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("ServerSocket打开失败");
        }

        System.out.println("begin server loop");
        defaultResponse = new Response();
        defaultResponse.setBody("server is working!");
        defaultResponse.setContentType("text/html");
        responseHandler = new ResponseHandler(configuration.getServerName(), configuration.getHttpVersion());
        defaultResponse.setHttpCode(200);
        while (true)
        {
            try
            {
                Socket socket = serverSocket.accept();
                Request request = getRequest(socket);
                App appToRun = hostMap.get(request.getHostName());
                Response response = null;
                if (appToRun == null)
                {
                    appToRun = defaultApp;
                }
                if (appToRun == null)
                {
                    response = defaultResponse;
                }
                else
                {
                    response = appToRun.run(request);
                }
                String msg = responseHandler.handleResponse(response);
                OutputStream output = socket.getOutputStream();
                try
                {
                    output.write(msg.getBytes());
                    output.flush();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                    System.out.println("结果返回失败");
                }
                socket.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
                System.out.println("socket创建失败");
            }
            catch (RequestException e)
            {
                e.printStackTrace();
                System.out.println("request创建失败");
            }


        }
    }


    private Request getRequest(Socket socket) throws IOException, RequestException
    {
        InputStream input = socket.getInputStream();
        byte[] buffer = new byte[2048];
        String requestString = null;
        while (true)
        {
            int readNum = input.read(buffer);
            if (requestString == null)
            {
                requestString = new String(buffer);
            }
            else
            {
                requestString += new String(buffer);
            }
            if (readNum < 2048)
            {
                break;
            }
        }

        Request request = new Request(requestString, socket);
        return request;
    }
}

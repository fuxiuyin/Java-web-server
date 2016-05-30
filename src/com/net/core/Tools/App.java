package com.net.core.Tools;


import com.net.core.Tools.Middleware.IRequestMiddleware;
import com.net.core.Tools.Middleware.IResponseMiddleware;
import com.net.core.Tools.configuration.AppConf;
import com.net.core.Tools.configuration.AppConfiguration;
import com.net.core.Tools.configuration.BaseConfiguration;
import com.net.core.app.UserApp;
import com.net.core.exception.AppLoadException;
import com.net.core.exception.ConfigurationLoadException;
import com.net.core.exception.HttpException;
import com.net.core.exception.MiddlewareException;
import com.net.core.http_module.Request;
import com.net.core.http_module.Response;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * Created by fuxiuyin on 16-5-24.
 */
public class App
{
    private String appName;
    private String appPath;
    private AppConfiguration appConfiguration = null;
    private List<IRequestMiddleware> requestMiddlewares;
    private List<IResponseMiddleware> responseMiddlewares;
    private UserApp app = null;
    private AppConf conf = null;


    public Response run(Request request)
    {
        // todo: middlwares
        return app.run(request);
    }


    public App(AppConf conf) throws AppLoadException
    {
        this.conf = conf;
        String appConfPath = conf.getPath() + "/config.json";

        try
        {
            appConfiguration = (AppConfiguration)BaseConfiguration.load(appConfPath);
        } catch (ConfigurationLoadException e)
        {
            throw new AppLoadException(conf.getPath(), "配置文件读取失败\n" + e.toString());
            //todo: event
        }
        appPath = conf.getPath();
        appName = appConfiguration.getAppName();

        File file = new File(appPath + "/" + appConfiguration.getJarName());
        URL url;
        try
        {
            url = file.toURL();
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            throw new AppLoadException(conf.getPath(), "jar加载失败\n" + e.toString());
        }

        URLClassLoader loader = new URLClassLoader(new URL[]{url});
        try
        {
            app = (UserApp)(loader.loadClass(appConfiguration.getClassName()).newInstance());
        }
        catch (ClassNotFoundException e)
        {
            throw new AppLoadException(conf.getPath(), "指定class未找到" + e.toString());
        }
        catch (InstantiationException | IllegalAccessException e)
        {
            throw new AppLoadException(conf.getPath(), "指定class实例化失败" + e.toString());
        }
        app.load();

    }


    public String getAppName()
    {
        return appName;
    }


    public String getAppPath()
    {
        return appPath;
    }


    public UserApp getApp()
    {
        return app;
    }


    public Response getResponse(Request request) throws HttpException
    {
        try
        {
            preRequest(request);
        }
        catch(MiddlewareException e)
        {
            throw new HttpException(e.getHttpCode());
        }
        Response response = app.run(request);
        try
        {
            preResponse(response);
        }
        catch(MiddlewareException e)
        {
            throw new HttpException(e.getHttpCode());
        }
        return response;
    }


    private void preRequest(Request request) throws MiddlewareException
    {
        for(IRequestMiddleware requestMiddleware : requestMiddlewares)
        {
            if(!requestMiddleware.handle(request))
            {
                break;
            }
        }
    }

    private void preResponse(Response response) throws MiddlewareException
    {
        for(IResponseMiddleware responseMiddleware : responseMiddlewares)
        {
            if(!responseMiddleware.handle(response))
            {
                break;
            }
        }
    }
}

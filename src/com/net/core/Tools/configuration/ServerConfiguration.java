package com.net.core.Tools.configuration;

import com.net.core.exception.ConfigurationLoadException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fuxiuyin on 16-5-29.
 */
public class ServerConfiguration extends BaseConfiguration
{
    private List<AppConf> allApp = null;
    private String httpVersion = null;
    private String serverName = null;
    private int workerNum = 3;

    protected ServerConfiguration(JSONObject jobj)
    {
        super(jobj);
    }


    public int getWorkerNum()
    {
        return workerNum;
    }


    public String getServerName()
    {
        return serverName;
    }


    public String getHttpVersion()
    {
        return httpVersion;
    }


    public List<AppConf> getAllApp()
    {
        return allApp;
    }

    @Override
    protected void readConfiguration() throws ConfigurationLoadException
    {
        String workerNumConf = (String)jobj.get("workerNum");
        Object tmp = jobj.get("httpVersion");
        if (tmp != null)
        {
            serverName = (String)tmp;
        }
        else
        {
            serverName = "Npache";
        }
        tmp = jobj.get("httpVersion");
        if (tmp != null)
        {
            httpVersion = (String)tmp;
        }
        else
        {
            httpVersion = "1.1";
        }

        if (workerNumConf != null)
        {
            workerNum = Integer.parseInt(workerNumConf);
        }

        JSONArray apps = (JSONArray)jobj.get("app");
        if (apps != null)
        {
            allApp = new ArrayList<>();
            for (Object app : apps)
            {
                //todo: 加载失败事件
                String host = (String)((JSONObject)app).get("host");
                String path = (String)((JSONObject)app).get("path");
                Object tmpAccessLogPath = ((JSONObject)app).get("accessLogPath");
                Object tmpErrorLogPath = ((JSONObject)app).get("errorLogPath");
                AppConf appConf = new AppConf(host, path);
                if (tmpAccessLogPath != null)
                {
                    appConf.setAccessLogPath((String)tmpAccessLogPath);
                }
                if (tmpErrorLogPath != null)
                {
                    appConf.setErrorLogPath((String)tmpErrorLogPath);
                }
            }
        }

    }
}



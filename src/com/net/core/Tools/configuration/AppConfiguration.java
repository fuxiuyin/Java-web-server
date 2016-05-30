package com.net.core.Tools.configuration;

import com.net.core.exception.ConfigurationLoadException;
import org.json.simple.JSONObject;

import java.util.*;

/**
 * Created by fuxiuyin on 16-5-29.
 */
public class AppConfiguration extends BaseConfiguration
{
    private String appVersion = null;
    private String appName = null;
    private Map<String, String> requestMiddlewares = null;
    private Map<String, String> responseMiddlewares = null;
    private String jarName = null;
    private String className = null;


    protected AppConfiguration(JSONObject jobj)
    {
        super(jobj);
    }


    public String getAppVersion()
    {
        return appVersion;
    }


    public String getAppName()
    {
        return appName;
    }


    public Set<String> getRequestMiddlewaresNameSet()
    {
        if (requestMiddlewares != null)
        {
            return requestMiddlewares.keySet();
        }
        else
        {
            return null;
        }
    }


    public String getJarName()
    {
        return jarName;
    }


    public Set<String> getResponseMiddlewaresNameSet()
    {
        if (requestMiddlewares != null)
        {
            return responseMiddlewares.keySet();
        }
        else
        {
            return null;
        }
    }


    public String getRequestMiddlewareArgument(String requestMiddlewareName)
    {
        return requestMiddlewares.get(requestMiddlewareName);
    }


    public String getResponseMiddlewareArgument(String responseMiddlewareName)
    {
        return responseMiddlewares.get(responseMiddlewareName);
    }


    public String getClassName()
    {
        return className;
    }


    @Override
    protected void readConfiguration() throws ConfigurationLoadException
    {
        Object tmpVersion = jobj.get("appVersion");
        Object tmpName = jobj.get("name");
        Object tmpJarName = jobj.get("jarName");
        Object tmpClassName = jobj.get("className");
        if (tmpVersion == null || tmpName == null || tmpJarName == null || tmpClassName == null)
        {
            throw new ConfigurationLoadException(filePath, "格式错误");
        }
        appVersion = tmpVersion.toString();
        appName = (String)tmpName;
        jarName = (String)tmpJarName;
        className = (String)tmpClassName;

        JSONObject requestMiddleware = (JSONObject)jobj.get("requestMiddleware");
        JSONObject responseMiddleware = (JSONObject)jobj.get("responseMiddleware");

        if (requestMiddleware != null)
        {
            requestMiddlewares = new HashMap<>();
            for (Object key : requestMiddleware.keySet())
            {
                requestMiddleware.put((String)key, (String)requestMiddleware.get(key));
            }
        }

        if (responseMiddleware != null)
        {
            responseMiddlewares = new HashMap<>();
            for (Object key : requestMiddleware.keySet())
            {
                responseMiddleware.put((String)key, (String)requestMiddleware.get(key));
            }
        }
    }
}

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

import java.util.List;

/**
 * Created by fuxiuyin on 16-5-24.
 */
class App
{
    private String appName;
    private String appPath;
    private AppConfiguration appConfiguration = null;
    private List<IRequestMiddleware> requestMiddlewares;
    private List<IResponseMiddleware> responseMiddlewares;
    private UserApp app = null;
    private AppConf conf = null;


    public App(AppConf conf) throws AppLoadException
    {
        this.conf = conf;
        String appConfPath = conf.getPath() + "config.jar";

        try
        {
            appConfiguration = (AppConfiguration)BaseConfiguration.load(appConfPath);
        } catch (ConfigurationLoadException e)
        {
            throw new AppLoadException(conf.getPath(), "配置文件读取失败\n" + e.toString());
            //todo: event
        }
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

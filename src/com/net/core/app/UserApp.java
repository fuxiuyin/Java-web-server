package com.net.core.app;

import com.net.core.Tools.Middleware.IRequestMiddleware;
import com.net.core.Tools.Middleware.IResponseMiddleware;
import com.net.core.exception.AppLoadException;
import com.net.core.exception.HttpException;
import com.net.core.exception.MiddlewareException;
import com.net.core.http_module.Request;
import com.net.core.http_module.Response;

import java.util.List;

/**
 * Created by fuxiuyin on 16-5-26.
 */
public abstract class UserApp
{
    public abstract Response run(Request request);

    public abstract void load() throws AppLoadException;
}

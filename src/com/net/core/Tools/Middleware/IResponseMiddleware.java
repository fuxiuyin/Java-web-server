package com.net.core.Tools.Middleware;

import com.net.core.exception.MiddlewareException;
import com.net.core.http_module.Response;

/**
 * Created by fuxiuyin on 16-5-26.
 */
public interface IResponseMiddleware
{
    boolean handle(Response response) throws MiddlewareException;
}

package com.net.core.Tools.Middleware;

import com.net.core.exception.MiddlewareException;
import com.net.core.http_module.Request;

/**
 * Created by fuxiuyin on 16-5-26.
 */
public interface IRequestMiddleware
{
    boolean handle(Request request) throws MiddlewareException;
    void setValue(String value);
}

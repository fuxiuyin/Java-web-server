package com.net.core.Tools.Middleware;

import com.net.core.exception.MiddlewareException;
import com.net.core.http_module.Request;

/**
 * Created by fuxiuyin on 16-5-29.
 */
public class CheckHttpVersion implements IRequestMiddleware
{
    String expVersion = null;

    @Override
    public boolean handle(Request request) throws MiddlewareException
    {
        if (!request.getHttpVersion().equals(expVersion))
        {
            throw new MiddlewareException("http版本错误", 505);
        }
        return true;
    }

    @Override
    public void setValue(String value)
    {
        expVersion = value;
    }
}

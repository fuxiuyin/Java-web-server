package com.net.core.exception;

/**
 * Created by fuxiuyin on 16-5-26.
 */
public class MiddlewareException extends NetServerException
{
    int httpCode;

    public MiddlewareException(String msg, int httpCode)
    {
        super(msg, 200);
        this.httpCode = httpCode;
    }


    public int getHttpCode()
    {
        return httpCode;
    }
}

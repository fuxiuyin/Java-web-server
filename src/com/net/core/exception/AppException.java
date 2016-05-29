package com.net.core.exception;

/**
 * Created by fuxiuyin on 16-5-26.
 */
public class AppException extends NetServerException
{
    public AppException(String msg, int code)
    {
        super(msg, 400 + code);
    }

}

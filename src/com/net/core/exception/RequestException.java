package com.net.core.exception;

/**
 * Created by fuxiuyin on 16-5-24.
 */
public class RequestException extends NetServerException
{
    public RequestException(String str, int code)
    {
        super(str, code + 100);
    }
}

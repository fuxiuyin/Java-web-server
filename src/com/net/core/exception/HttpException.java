package com.net.core.exception;

/**
 * Created by fuxiuyin on 16-5-26.
 */
public class HttpException extends NetServerException
{
    private int httpErrorCode;

    public HttpException(int code)
    {
        super("request处理失败", 300);
    }


    public int getHttpErrorCode()
    {
        return httpErrorCode;
    }
}

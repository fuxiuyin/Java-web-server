package com.net.core.exception;

/**
 * Created by fuxiuyin on 16-5-24.
 */
public class NetServerException extends Exception
{
    private int errorCode;


    public NetServerException(String msg, int code)
    {
        super(msg);
        errorCode = code;
    }


    public int getCode()
    {
        return errorCode;
    }
}

package com.net.core.exception;

import org.json.simple.parser.ParseException;

/**
 * Created by fuxiuyin on 16-5-29.
 */
public class ConfigurationLoadException extends NetServerException
{
    private Exception exception = null;

    public ConfigurationLoadException(String fileName, String msg, Exception e)
    {
        super(fileName + "加载失败\n" + msg, 500);
        exception = e;
    }


    public ConfigurationLoadException(String fileName, String msg)
    {
        super(fileName + "加载失败\n" + msg, 500);
    }


    public Exception getException()
    {
        return exception;
    }
}

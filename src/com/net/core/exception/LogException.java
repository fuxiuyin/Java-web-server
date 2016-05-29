package com.net.core.exception;

/**
 * Created by fuxiuyin on 16-5-26.
 */
public class LogException extends NetServerException
{
    private String logFileName;

    public LogException(String logFileName)
    {
        super("log" + logFileName + "写入失败", 400);
        this.logFileName = logFileName;
    }


    public String getLogFileName()
    {
        return logFileName;
    }
}

package com.net.core.Tools.configuration;

/**
 * Created by fuxiuyin on 16-5-29.
 */
public class AppConf
{
    private String host;
    private String path;
    private String accessLogPath;
    private String errorLogPath;


    public AppConf(String host, String path)
    {
        this.host = host;
        this.path = path;
        this.accessLogPath = this.host + "access.log";
        this.errorLogPath = this.host + "error.log";
    }


    public String getAccessLogPath()
    {
        return accessLogPath;
    }


    public String getErrorLogPath()
    {
        return errorLogPath;
    }


    public void setAccessLogPath(String accessLogPath)
    {
        this.accessLogPath = accessLogPath;
    }


    public void setErrorLogPath(String errorLogPath)
    {
        this.errorLogPath = errorLogPath;
    }


    public String getHost()
    {
        return host;
    }


    public String getPath()
    {
        return path;
    }
}

package com.net.core.exception;

/**
 * Created by fuxiuyin on 16-5-26.
 */
public class AppLoadException extends AppException
{
    private String appName;

    public AppLoadException(String appName, String msg)
    {
        super(appName + "加载失败\n" + msg, 10);
        this.appName = appName;
    }


    public String getAppName()
    {
        return appName;
    }
}

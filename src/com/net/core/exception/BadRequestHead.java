package com.net.core.exception;

/**
 * Created by fuxiuyin on 16-5-24.
 */
public class BadRequestHead extends RequestException
{
    public BadRequestHead(String str)
    {
        super("错误的请求头格式\n" + str, 10);
    }

}

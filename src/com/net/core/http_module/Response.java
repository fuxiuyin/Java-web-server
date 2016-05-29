package com.net.core.http_module;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by fuxiuyin on 16-5-24.
 */
public class Response
{
    private Map<String, String> header = new HashMap<>();
    private String body = "";
    private ResponseCookies cookies = null;
    private int httpCode = 200;


    public String getBody()
    {
        return body;
    }


    public Map<String, String> getHeader()
    {
        return header;
    }

    public int getHttpCode()
    {
        return httpCode;
    }


    public void setHttpCode(int code)
    {
        httpCode = code;
    }


    public void setBody(String body)
    {
        this.body = body;
    }


    public void setCookie(ResponseCookie cookie)
    {
        cookies.setCookie(cookie.getKey(), cookie);
    }
}

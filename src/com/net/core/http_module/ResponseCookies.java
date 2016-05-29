package com.net.core.http_module;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by fuxiuyin on 16-5-29.
 */
public class ResponseCookies
{
    private Map<String, ResponseCookie> cookies = new HashMap<>();


    public void setCookie(String key, ResponseCookie cookie)
    {
        cookies.put(key, cookie);
    }


    public void delCookie(String key)
    {
        cookies.remove(key);
    }


    public Set<String> getCookieNameSet()
    {
        return cookies.keySet();
    }


    public ResponseCookie getCookie(String key)
    {
        return cookies.get(key);
    }


    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, ResponseCookie> entry : cookies.entrySet())
        {
            sb.append(String.format("Set-Cookie: %s\r\n", entry.getValue().toString()));
        }
        return sb.toString();
    }

}
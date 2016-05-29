package com.net.core.http_module;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuxiuyin on 16-5-24.
 */
public class RequestCookies
{
    private Map<String, String> cookies = new HashMap<>();


    public RequestCookies(String rCookieStr)
    {
        for(String keyAndValueStr : rCookieStr.split("; "))
        {
            String[] keyAndValue = keyAndValueStr.split("=");
            cookies.put(keyAndValue[0], keyAndValue[1]);
        }
    }


    public String getCookieValue(String key)
    {
        return cookies.get(key);
    }

}

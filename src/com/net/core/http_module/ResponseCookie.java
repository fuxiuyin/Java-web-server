package com.net.core.http_module;

import java.util.Date;

/**
 * Created by fuxiuyin on 16-5-29.
 */
public class ResponseCookie
{
    private String key = null;
    private String value = null;
    private String path = "/";
    private String domain = null;
    private Date expires = null;



    public ResponseCookie(String key, String value)
    {
        this.key = key;
        this.value = value;
    }


    public void setPath(String path)
    {
        this.path = path;
    }


    public void setDomain(String domain)
    {
        this.domain = domain;
    }


    public void setExpires(Date date)
    {
        this.expires = date;
    }


    public String getKey()
    {
        return key;
    }


    public String getValue()
    {
        return value;
    }


    public String getPath()
    {
        return path;
    }


    public String getDomain()
    {
        return domain;
    }


    public Date getExpires()
    {
        return expires;
    }


    @Override
    public String toString()
    {
        String result = String.format("%s=%s", key, value);
        if(expires != null)
        {
            result = String.format("%s; expires=%s", result, expires.toGMTString());
        }

        if(domain != null)
        {
            result = String.format("%s; domain=%s", result, domain);
        }

        result = String.format("%s; path=%s", result, path);
        return result;
    }
}

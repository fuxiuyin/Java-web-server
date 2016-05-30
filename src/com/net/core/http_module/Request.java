package com.net.core.http_module;

import com.net.core.exception.BadRequestHead;
import com.net.core.exception.RequestException;

import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fuxiuyin on 16-5-24.
 */


public class Request
{
    private String method;
    private String hostName;
    private String connectionType = "close";
    private String httpVersion;
    private String path;
    private String clientIP;
    private int clientPoint;
    private Map<String, String> meta = new HashMap<>();
    private Map<String, String> get = new HashMap<>();
    private Map<String, String> post = new HashMap<>();
    private RequestCookies cookies = null;
    private Socket socket;


    public Request(String request, Socket socket) throws RequestException
    {
        this.socket = socket;
        InetSocketAddress address = (InetSocketAddress)socket.getRemoteSocketAddress();
        clientIP = address.getAddress().getHostAddress();
        clientPoint = address.getPort();
        String[] lines = request.split("\r\n");
        fillHostAndVersion(lines[0]);
        for (int i = 1; i < lines.length; ++i)
        {
            String[] keyAndValue = lines[i].split(": ");
            if(keyAndValue.length == 2)
            {
                switch(keyAndValue[0])
                {
                    case "Host":
                    case "host":
                        hostName = keyAndValue[1];
                        break;
                    case "Connection":
                    case "connection":
                        connectionType = keyAndValue[1];
                        break;
                    case "Cookie":
                    case "cookie":
                        cookies = new RequestCookies(keyAndValue[1]);
                        break;
                    default:
                        meta.put(keyAndValue[0], keyAndValue[1]);
                }

            }
            else
            {
                break;
            }
        }
        String[] tmpHost = hostName.split(":");
        if (tmpHost.length > 1)
        {
            hostName = tmpHost[0];
        }
    }


    private void fillHostAndVersion(String str) throws BadRequestHead
    {
        String[] tmp = str.split(" ");
        if(tmp.length != 3)
        {
            throw new BadRequestHead("请求头第一行必须为\n \"method host http-version\"\n得到" + str);
        }

        method = tmp[0].toUpperCase();

        path = tmp[1];

        String[] pathAndGetData = path.split("\\?");
        if(pathAndGetData.length > 1)
        {
            if (pathAndGetData.length > 2)
            {
                throw new BadRequestHead("url中包含重复的查询标识符\"?\"");
            }
            path = pathAndGetData[0];
            for(String kV : pathAndGetData[1].split("&"))
            {
                String[] keyAndValue = kV.split("=");
                get.put(keyAndValue[0], keyAndValue[1]);
            }
        }


        if(tmp[2].endsWith("1.1"))
        {
            httpVersion = "1.1";
        }
        else if(tmp[2].endsWith("1"))
        {
            httpVersion = "1.0";
        }
        else
        {
            throw new BadRequestHead("\"" + tmp[2] + "\"" + "不是一个合适的http-version");
        }
    }


    public String getPath()
    {
        return path;
    }


    public String getMethod()
    {
        return method;
    }


    public String getHttpVersion()
    {
        return httpVersion;
    }


    public String getConnectionType()
    {
        return connectionType;
    }


    public String getHostName()
    {
        return hostName;
    }


    public String getMetaValue(String key)
    {
        return meta.get(key);
    }


    public String getPostData(String key)
    {
        return post.get(key);
    }


    public String getGetData(String key)
    {
        return get.get(key);
    }


    public String getCookieValue(String key)
    {
        return cookies.getCookieValue(key);
    }


    public Socket getSocket()
    {
        return socket;
    }
}

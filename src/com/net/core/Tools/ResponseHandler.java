package com.net.core.Tools;


import com.net.core.http_module.Response;

import java.util.Date;
import java.util.Map;

/**
 * Created by fuxiuyin on 16-5-29.
 */
public class ResponseHandler
{
    private String serverName;
    private String httpVersion;


    public ResponseHandler(String serverName, String httpVersion)
    {
        this.serverName = serverName;
        this.httpVersion = httpVersion;
    }


    public String httpCodeToString(int httpCode)
    {
        switch (httpCode)
        {
            case 100:
                return "Continue";
            case 101:
                return "Switching Protocols";
            case 102:
                return "Processing";
            case 200:
                return "OK";
            case 201:
                return "Created";
            case 202:
                return "Accepted";
            case 203:
                return "Non-Authoritative Information";
            case 204:
                return "Non-Authoritative Information";
            case 205:
                return "Reset Content";
            case 206:
                return "Partial Content";
            case 300:
                return "Multiple Choices";
            case 301:
                return "Moved Permanently";
            case 302:
                return "Move temporarily";
            case 303:
                return "See Other";
            case 304:
                return "Not Modified";
            case 305:
                return "Use Proxy";
            case 306:
                return "Switch Proxy";
            case 307:
                return "Temporary Redirect";
            case 400:
                return "Bad Request";
            case 401:
                return "Unauthorized";
            case 403:
                return "Forbidden";
            case 404:
                return "Not Found";
            case 405:
                return "Method Not Allowed";
            case 406:
                return "Not Acceptable";
            case 407:
                return "Proxy Authentication Required";
            case 408:
                return "Request Timeout";
            case 409:
                return "Conflict";
            case 410:
                return "Gone";
            case 411:
                return "Length Required";
            case 412:
                return "Precondition Failed";
            case 413:
                return "Request Entity Too Large";
            case 414:
                return "Request-URI Too Long";
            case 500:
                return "Internal Server Error";
            case 501:
                return "Not Implemented";
            case 502:
                return "Bad Gateway";
            case 503:
                return "Service Unavailable";
            case 504:
                return "Gateway Timeout";
            case 505:
                return "HTTP Version Not Supported";
            default:
                return "unknown";
        }
    }


    public String handleResponse(Response response)
    {
        Map<String, String> header = response.getHeader();
        header.put("Server", serverName);
        if (!header.containsKey("Content-Type"))
        {
            header.put("Content-Type", "application/octet-stream");
        }
        if (!header.containsKey("Last-Modified"))
        {
            Date date = new Date();
            header.put("Last-Modified", date.toGMTString());
        }

        String body = response.getBody();

        header.put("Content-Length", String.valueOf(body.getBytes().length));

        StringBuilder sb = new StringBuilder();

        int httpCode = response.getHttpCode();
        sb.append(String.format("HTTP/%s %d %s\r\n", this.httpVersion, httpCode, httpCodeToString(httpCode)));
        for (String headerKey : header.keySet())
        {
            sb.append(String.format("%s: %s\r\n", headerKey, header.get(headerKey)));
        }
        sb.append("\r\n");

        sb.append(body);

        return sb.toString();
    }
}

package com.net.core.app;

import com.net.core.exception.AppLoadException;
import com.net.core.http_module.Request;
import com.net.core.http_module.Response;

/**
 * Created by fuxiuyin on 16-5-30.
 */
public class TestApp extends UserApp
{
    @Override
    public Response run(Request request)
    {
        Response response = new Response();
        response.setBody("hello world");
        response.setContentType("text/html");
        return response;
    }

    @Override
    public void load() throws AppLoadException
    {
        System.out.println("test app load success");
    }
}

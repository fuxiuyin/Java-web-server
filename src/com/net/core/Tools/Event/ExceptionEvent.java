package com.net.core.Tools.Event;

import com.net.core.Tools.Log.Logger;
import com.net.core.exception.LogException;
import com.net.core.exception.NetServerException;

import java.util.Date;

/**
 * Created by fuxiuyin on 16-5-26.
 */
public class ExceptionEvent extends BaseEvent
{
    protected NetServerException exception;


    public static ExceptionEvent exceptionEventMaker(NetServerException netServerException)
    {
        Date date = new Date();
        return new ExceptionEvent(netServerException, date);
    }


    private ExceptionEvent()
    {

    }


    private ExceptionEvent(NetServerException exception, Date date)
    {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement traceElement : exception.getStackTrace())
        {
            sb.append("\tat" + traceElement);
        }
        msg = sb.toString();
        eventHappenTime = date;
        this.exception = exception;
    }

    @Override
    public void log(Logger logger)
    {
        logger.error(msg, eventHappenTime);
    }

    public void destroy()
    {
        super.destroy();
    }


    public NetServerException getException()
    {
        return exception;
    }
}

package com.net.core.Tools.Event;

import com.net.core.Tools.Log.Logger;

import java.util.Date;

/**
 * Created by fuxiuyin on 16-5-26.
 */
public abstract class BaseEvent implements IEventAction
{
    protected boolean alive = true;
    protected String eventName;
    protected Date eventHappenTime;
    protected String msg = "null";


    public BaseEvent()
    {
        eventHappenTime = new Date();
        eventName = "null";
    }


    public BaseEvent(String msg)
    {
        this.msg = msg;
        eventHappenTime = new Date();
        eventName = "null";
    }


    public void destroy()
    {
        alive = false;
    }

    @Override
    public void log(Logger logger)
    {
        logger.info(msg, eventHappenTime);
    }


    public boolean isAlive()
    {
        return alive;
    }


    public String getEventName()
    {
        return eventName;
    }
}

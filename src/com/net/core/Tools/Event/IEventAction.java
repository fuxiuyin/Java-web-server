package com.net.core.Tools.Event;

import com.net.core.Tools.Log.Logger;
import com.net.core.exception.LogException;

/**
 * Created by fuxiuyin on 16-5-24.
 */
public interface IEventAction
{
    public void log(Logger logger);
    public void destroy();
}

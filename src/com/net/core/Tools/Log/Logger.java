package com.net.core.Tools.Log;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

/**
 * Created by fuxiuyin on 16-5-26.
 */
public class Logger
{
    private String logFileName;
    private java.util.logging.Logger logger;
    private String loggerName;
    private ConsoleHandler consoleHandler = null;

    public Logger(String fileName) throws IOException
    {
        new Logger(fileName, "null");
    }


    public Logger(String fileName, String loggerName) throws IOException
    {
        logger = java.util.logging.Logger.getLogger(loggerName);
        logger.setLevel(Level.INFO);
        FileHandler fileHandler = new FileHandler(fileName);
        fileHandler.setLevel(Level.INFO);
        fileHandler.setFormatter(new MyLogHanderFormatter());
        logger.addHandler(fileHandler);
    }


    public void openConsoleOutPut()
    {
        if(consoleHandler == null)
        {
            consoleHandler = new ConsoleHandler();
            logger.addHandler(consoleHandler);
        }
    }


    public void closeConsoleOutPut()
    {
        if(consoleHandler != null)
        {
            logger.removeHandler(consoleHandler);
        }
    }


    public void info(String msg, Date date)
    {
        LogRecord record = new LogRecord(Level.INFO, msg);
        record.setMillis(date.getTime());
        logger.log(record);
    }


    public void info(String msg)
    {
        logger.info(msg);
    }


    public void warning(String msg, Date date)
    {
        LogRecord record = new LogRecord(Level.WARNING, msg);
        record.setMillis(date.getTime());
        logger.log(record);
    }


    public void warning(String msg)
    {
        logger.info(msg);
    }


    public void error(String msg, Date date)
    {
        LogRecord record = new LogRecord(Level.SEVERE, msg);
        record.setMillis(date.getTime());
        logger.log(record);
    }


    public void error(String msg)
    {
        logger.severe(msg);
    }


    public String getLogFileName()
    {
        return logFileName;
    }


    public String getLoggerName()
    {
        return loggerName;
    }
}


class MyLogHanderFormatter extends Formatter
{
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public String format(LogRecord record)
    {
        return String.format("%s %s: %s\n", df.format(record.getMillis()), record.getLevel(), record.getMessage());
    }
}
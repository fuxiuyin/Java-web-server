package com.net.core.Tools.configuration;
import com.net.core.exception.ConfigurationLoadException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.nio.Buffer;

/**
 * Created by fuxiuyin on 16-5-24.
 */
public class BaseConfiguration
{
    protected static JSONParser parser = new JSONParser();
    protected JSONObject jobj = null;
    protected String filePath;

    protected BaseConfiguration(JSONObject jobj)
    {
        this.jobj = jobj;
    }


    protected static String readAllFileContent(String filePath) throws ConfigurationLoadException
    {
        BufferedReader reader = null;
        FileInputStream fileInputStream = null;
        try
        {
            fileInputStream = new FileInputStream(filePath);
        }
        catch (FileNotFoundException e)
        {
            throw new ConfigurationLoadException(filePath, "文件不存在", e);
        }

        InputStreamReader inputStreamReader = null;
        try
        {
            inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
        } catch (UnsupportedEncodingException e)
        {
            throw new ConfigurationLoadException(filePath, "文件编码错误", e);
        }

        reader = new BufferedReader(inputStreamReader);
        String tmpString = null;
        String result = "";
        try
        {
            while((tmpString = reader.readLine()) != null)
            {
                result += tmpString;
            }
        } catch (IOException e)
        {
            throw new ConfigurationLoadException(filePath, "文件读取失败", e);
        }

        try
        {
            inputStreamReader.close();
            fileInputStream.close();
            reader.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return result;
    }


    public static BaseConfiguration load(String filePath) throws ConfigurationLoadException
    {
        JSONObject jobj;
        String fileContent = readAllFileContent(filePath);
        try
        {
            jobj = (JSONObject) parser.parse(fileContent);
        }
        catch (ParseException e)
        {
            throw new ConfigurationLoadException(filePath, "文件格式错误", e);
        }

        Object version = jobj.get("appVersion");
        BaseConfiguration configuration;
        if(version == null)
        {
            configuration = new ServerConfiguration(jobj);
        }
        else
        {
            configuration = new AppConfiguration(jobj);
        }

        configuration.readConfiguration();
        configuration.filePath = filePath;
        return configuration;
    }

    protected void readConfiguration() throws ConfigurationLoadException
    {

    }
}

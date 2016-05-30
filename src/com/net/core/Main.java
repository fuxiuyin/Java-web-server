package com.net.core;
import com.net.core.Tools.Log.Log;
import com.net.core.Tools.configuration.BaseConfiguration;
import com.net.core.Tools.configuration.ServerConfiguration;
import com.net.core.http_module.Request;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.*;

public class Main {

    public static void main(String[] args) throws Exception
    {
        //ServerSocket server = new ServerSocket(8080, 1, InetAddress.getByName("127.0.0.1"));

        Core core = new Core();


//        while(true)
//        {
//            Socket socket = server.accept();
//            InputStream input = socket.getInputStream();
//            byte[] buffer = new byte[2048];
//            String requestString = null;
//            while(true)
//            {
//                int readNum;
//                try
//                {
//                    readNum = input.read(buffer);
//                    if (requestString == null)
//                    {
//                        requestString = new String(buffer);
//                    } else
//                    {
//                        requestString += new String(buffer);
//                    }
//                } catch (IOException e)
//                {
//                    e.printStackTrace();
//                    break;
//                }
//                if (readNum < 2048)
//                {
//                    break;
//                }
//            }
//
//            Request request = new Request(requestString, (InetSocketAddress)socket.getRemoteSocketAddress(), socket);
//
//            String out = "lalala";
//            OutputStream output = socket.getOutputStream();
//            try
//            {
//                output.write(out.getBytes());
//                output.flush();
//            }
//            catch(IOException e)
//            {
//                e.printStackTrace();
//            }
//            socket.close();
//        }
    }



}

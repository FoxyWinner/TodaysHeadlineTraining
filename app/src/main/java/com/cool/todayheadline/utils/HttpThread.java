package com.cool.todayheadline.utils;

import android.os.Handler;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpThread
{
    private final static int CONNECT_OUT_TIME = 5000;
    private String url;
    private String name;
    private String pwd;
    private TextView response;
    public int status;
    private int id;
    private Handler handler;



    public HttpThread(String url, String name, String pwd, TextView response,
                      Handler handler, int status, int id)
    {
        super();
        this.url = url;
        this.name = name;
        this.pwd = pwd;
        this.response = response;
        this.handler = handler;
        this.status = status;
        this.id = id;
    }

    public void doPost()
    {
        try
        {

            URL httpUrl = new URL(url);
            // 第二步：根据URL对象，获取HttpURLConnection对象
            HttpURLConnection connection = (HttpURLConnection) httpUrl
                    .openConnection();
            connection.setConnectTimeout(CONNECT_OUT_TIME);
            connection.setReadTimeout(CONNECT_OUT_TIME);
            connection.setRequestMethod("POST");
            connection.setDoInput(true);

            OutputStream out = connection.getOutputStream();
            String content = "username=" + name + "&password=" + pwd;
            // 无论服务器转码与否，这里不需要转码，因为Android系统自动已经转码为utf-8啦
            out.write(content.getBytes());
            out.flush();
            out.close();

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            final StringBuffer buffer = new StringBuffer();
            String str = null;
            while ((str = reader.readLine()) != null)
            {
                buffer.append(str);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(buffer.toString());
            status = jsonObject.getInt("status");


            JSONObject jsonObject1 = jsonObject.getJSONObject("data");
            id = jsonObject1.getInt("id");


        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (JSONException e)
        {
            e.printStackTrace();
        }


    }

    public void run()
    {
        doPost();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }


    public String getUrl()
    {
        return url;
    }

    public void setUrl(String url)
    {
        this.url = url;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }


    public TextView getResponse()
    {
        return response;
    }

    public void setResponse(TextView response)
    {
        this.response = response;
    }

    public int getStatus()
    {
        return status;
    }

}

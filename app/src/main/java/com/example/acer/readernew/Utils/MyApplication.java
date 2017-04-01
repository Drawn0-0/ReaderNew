package com.example.acer.readernew.Utils;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by acer on 2017/3/27.
 */

public class MyApplication extends Application {

    public static RequestQueue requestQueue;

    public static RequestQueue getRequestQueue(){
        return requestQueue;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //实例化Volley全局请求队列
        requestQueue = Volley.newRequestQueue(getApplicationContext());
    }
}

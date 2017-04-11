package com.example.acer.readernew.Utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by acer on 2017/3/27.
 * Volley的单例模式
 */

public class VolleySingleton{

    private static Context mContext;
    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;


    private VolleySingleton(Context context) {
        mContext=context;
        mRequestQueue = getRequestQueue();
    }

    /**
     * @return 返回Volley的实例
     */
    public static synchronized VolleySingleton newInstance(){

        if (mInstance == null){
            mInstance = new VolleySingleton(mContext);
        }
        return mInstance;
    };

    /**
     * @return 请求队列
     */
    private RequestQueue getRequestQueue(){
        if (mRequestQueue == null){
            // getApplicationContext() 是关键, 它避免了你
            //传递进Activity或BroadcastReceiver导致的内存泄漏
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        }
        return mRequestQueue;
    }

    /**
     * @param req 请求
     * @param <T> 泛型
     *           添加请求到请求队列
     */
    public<T> void addToRequestQueue(Request<T> req){
        getRequestQueue().add(req);
    }
}

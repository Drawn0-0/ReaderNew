package com.example.acer.readernew.Utils;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by acer on 2017/3/27.
 * 处理网络请求
 */

public class NetUtils {
    public static void load(String url, final OnStringListener listener,String TAG) {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                listener.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.onError(error);
            }
        });
        request.setTag(TAG);
        MyApplication.getRequestQueue().add(request);
    }
}

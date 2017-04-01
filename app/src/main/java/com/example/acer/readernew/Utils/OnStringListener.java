package com.example.acer.readernew.Utils;

import com.android.volley.VolleyError;

/**
 * Created by acer on 2017/3/27.
 */

public interface OnStringListener {
    /**
     * 成功时回调
     * @param result    成功时的结果
     */
    void onSuccess(String result);

    /**
     * 失败时回调
     * @param error    失败的信息
     */
    void onError(VolleyError error);
}

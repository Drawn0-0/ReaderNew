package com.example.acer.readernew;

import android.view.View;

/**
 * Created by acer on 2017/4/21.
 */

public interface BaseView<T> {
    void setPresenter(T presenter);
    void initView(View view);
}

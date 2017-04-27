package com.example.acer.readernew.Interface;

import com.example.acer.readernew.Bean.NewsBean;

/**
 * Created by acer on 2017/4/21.
 */

public interface Result {
    void resultSuccess(NewsBean bean);
    void loadMore(NewsBean bean);
}

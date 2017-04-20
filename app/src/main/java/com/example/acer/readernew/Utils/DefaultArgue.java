package com.example.acer.readernew.Utils;

/**
 * Created by acer on 2017/3/24.
 * 设置默认的参数
 */

public interface DefaultArgue {
    //appkey
    String appkey = "620a4290af5ae38a";
    //默认频道
    String channel = "头条";
    //默认新闻数量，默认10，最大40
    int num = 10;
    //起始位置，默认0
    int start = 0;

    interface Channel {
        String headline = "头条";
        String news = "新闻";
        String finance = "财经";
        String sport = "体育";
        String entertainment = "娱乐";
        String military = "军事";
        String education = "教育";
        String technology = "科技";
        String NBA = "NBA";
        String stock = "股票";
        String constellation = "星座";
        String woman = "女性";
        String health = "健康";
        String parenting = "育儿";
    }
}

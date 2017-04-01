package com.example.acer.readernew.Utils;

import static com.example.acer.readernew.Utils.DefaultArgue.appkey;

/**
 * Created by acer on 2017/3/24.
 * 网址请求
 */

public class API {

    /**
     * 获取频道的请求网址
     */
     public final static String getChannel = "http://api.jisuapi.com/news/channel?appkey=" + appkey;

    /**
     * @param channel 请求的频道 默认头条
     * @param start   起始位置 默认0
     * @param num     数量 默认10 最大40
     * @return 新闻api
     * 获取新闻的请求网址
     */
    public static String getGetNews(String channel, int start, int num) {
        return "http://api.jisuapi.com/news/get?channel=" + channel + "&start=" + start + "&num=" + num + "&appkey=" + appkey;
    }

    public static String getGetNews() {
        return "http://api.jisuapi.com/news/get?channel=" + DefaultArgue.channel + "&start=" + DefaultArgue.start + "&num=" + DefaultArgue.num + "&appkey=" + appkey;
    }

    /**
     * @param keyword 查询的关键字
     * @return 查询的请求网址
     * 获取查询新闻的请求网址
     */
    public static String getSearch(String keyword) {
        return "http://api.jisuapi.com/news/search?keyword=" + keyword + "&appkey=" + appkey;
    }
}

package com.example.acer.readernew.Bean;

/**
 * Created by acer on 2017/4/18.
 * EventBus发送的消息类
 */

public class MessageEvent {
    public NewsBean.ResultBean.ListBean getBean() {
        return bean;
    }

    private final NewsBean.ResultBean.ListBean bean;

    public MessageEvent(NewsBean.ResultBean.ListBean bean) {
        this.bean = bean;
    }
}

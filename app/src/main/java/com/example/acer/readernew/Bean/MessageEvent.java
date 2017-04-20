package com.example.acer.readernew.Bean;

/**
 * Created by acer on 2017/4/18.
 * EventBus发送的消息类
 */

public class MessageEvent {
    private final NewsBean bean;

    public MessageEvent(NewsBean bean) {
        this.bean=bean;
    }

    public NewsBean getBean() {
        return bean;
    }
}

package com.example.acer.readernew.Bean;

import java.util.List;

/**
 * Created by acer on 2017/3/27.
 */

public class ChannelBean {
    /**
     * status : 0
     * msg : ok
     * result : ["头条","新闻","财经","体育","娱乐","军事","教育","科技","NBA","股票","星座","女性","健康","育儿"]
     */

    private String status;
    private String msg;
    private List<String> result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<String> getResult() {
        return result;
    }

    public void setResult(List<String> result) {
        this.result = result;
    }
}

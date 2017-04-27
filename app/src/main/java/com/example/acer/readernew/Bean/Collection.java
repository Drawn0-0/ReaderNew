package com.example.acer.readernew.Bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by acer on 2017/4/21.
 */

@Entity
public class Collection {
    @Id(autoincrement = true)
    private Long id;
    private String title;
    private String time;
    private String src;
    private String category;
    private String pic;
    private String content;
    private String url;
    private String weburl;
    @Generated(hash = 136705694)
    public Collection(Long id, String title, String time, String src,
            String category, String pic, String content, String url,
            String weburl) {
        this.id = id;
        this.title = title;
        this.time = time;
        this.src = src;
        this.category = category;
        this.pic = pic;
        this.content = content;
        this.url = url;
        this.weburl = weburl;
    }
    @Generated(hash = 1149123052)
    public Collection() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTime() {
        return this.time;
    }
    public void setTime(String time) {
        this.time = time;
    }
    public String getSrc() {
        return this.src;
    }
    public void setSrc(String src) {
        this.src = src;
    }
    public String getCategory() {
        return this.category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getContent() {
        return this.content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public String getUrl() {
        return this.url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getWeburl() {
        return this.weburl;
    }
    public void setWeburl(String weburl) {
        this.weburl = weburl;
    }
    public void setId(Long id) {
        this.id = id;
    }
}

package com.example.acer.readernew.Activities.Detail;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.WebView;

import com.example.acer.readernew.Bean.Collection;
import com.example.acer.readernew.Bean.NewsBean;
import com.example.acer.readernew.Utils.BaseApplication;
import com.example.acer.readernew.Utils.DiskCacheManager;

/**
 * Created by acer on 2017/4/21.
 */

public class DetailPresenter implements DetailContact.Presenter {
    private Context context;
    private DetailContact.View view;
    private NewsBean.ResultBean.ListBean bean;

    public void setData(NewsBean.ResultBean.ListBean bean) {
        this.bean = bean;
        Collection collection = new Collection();
        collection.setTitle(bean.getTitle());
        collection.setUrl(bean.getUrl());
        collection.setCategory(bean.getCategory());
        collection.setContent(bean.getContent());
        collection.setPic(bean.getPic());
        collection.setSrc(bean.getSrc());
        collection.setTime(bean.getTime());
        collection.setWeburl(bean.getWeburl());
    }

    public DetailPresenter(Context context, DetailContact.View view) {
        this.context = context;
        this.view = view;
        this.view.setPresenter(this);
    }

    @Override
    public void start() {

    }

    @Override
    public void openInBrowser() {

    }

    @Override
    public void shareAsText() {

    }

    @Override
    public void openUrl(WebView webView, String url) {

    }

    @Override
    public void copyText() {

    }

    @Override
    public void copyLink() {

    }

    @Override
    public void addToOrDeleteFromBookmarks() {

    }

    @Override
    public boolean queryIfIsBookmarked() {

        return false;
    }

    @Override
    public void requestData() {
        view.showCover(bean.getPic());
        view.setTitle(bean.getTitle());
        view.showResult(bean.getContent());
    }

    private boolean checkNull(String s) {
        return TextUtils.isEmpty(s);
    }
}

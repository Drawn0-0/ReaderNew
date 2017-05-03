package com.example.acer.readernew.Activities.Detail;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.net.Uri;
import android.support.customtabs.CustomTabsIntent;
import android.support.v7.preference.PreferenceManager;
import android.text.Html;
import android.text.TextUtils;
import android.webkit.WebView;

import com.example.acer.readernew.Activities.InnerBrower.CustomTabActivityHelper;
import com.example.acer.readernew.Activities.InnerBrower.WebviewFallback;
import com.example.acer.readernew.Bean.Collection;
import com.example.acer.readernew.Bean.NewsBean;
import com.example.acer.readernew.Bean.history;
import com.example.acer.readernew.Dao.CollectionDao;
import com.example.acer.readernew.R;
import com.example.acer.readernew.Utils.BaseApplication;
import com.example.acer.readernew.Utils.DaoUtil;

import java.util.List;

import static android.content.Context.CLIPBOARD_SERVICE;

/**
 * Created by acer on 2017/4/21.
 */

public class DetailPresenter implements DetailContact.Presenter {
    private Context context;
    private DetailContact.View view;
    private NewsBean.ResultBean.ListBean bean;
    private Collection collection;
    private final SharedPreferences sp;

    public void setData(NewsBean.ResultBean.ListBean bean) {
        this.bean = bean;
        collection = new Collection();
        history history = new history();

        collection.setTitle(bean.getTitle());
        collection.setUrl(bean.getUrl());
        collection.setCategory(bean.getCategory());
        collection.setContent(bean.getContent());
        collection.setPic(bean.getPic());
        collection.setSrc(bean.getSrc());
        collection.setTime(bean.getTime());
        collection.setWeburl(bean.getWeburl());

        history.setTitle(bean.getTitle());
        history.setCategory(bean.getCategory());
        history.setContent(bean.getContent());
        history.setPic(bean.getPic());
        history.setSrc(bean.getSrc());
        history.setTime(bean.getTime());
        history.setUrl(bean.getUrl());
        history.setWeburl(bean.getWeburl());
        BaseApplication.getDaoSession().getHistoryDao().insertOrReplace(history);
    }

    public DetailPresenter(Context context, DetailContact.View view) {
        this.context = context;
        this.view = view;
        this.view.setPresenter(this);
        sp = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public void start() {

    }

    @Override
    public void openInBrowser() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(bean.getUrl()));
        context.startActivity(intent);
    }

    @Override
    public void shareAsText() {
        Intent share = new Intent().setAction(Intent.ACTION_SEND).setType("text/plain");
        String shareText = bean.getTitle() + " " + bean.getUrl() + context.getString(R.string.share_content);
        share.putExtra(Intent.EXTRA_TEXT, shareText);
        context.startActivity(Intent.createChooser(share, context.getString(R.string.share_to)));
    }

    @Override
    public void openUrl(WebView webView) {
        if (sp.getBoolean("in_app_browser", true)) {
            String url = bean.getUrl();
            CustomTabsIntent.Builder intent = new CustomTabsIntent.Builder();
            CustomTabActivityHelper.openCustomTab((Activity) context, intent.build(), Uri.parse(url), new WebviewFallback());
        } else {
            openInBrowser();
        }
    }

    @Override
    public void copyText() {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData data = null;
        String copyText = bean.getTitle() + "\n" + Html.fromHtml(bean.getContent()).toString();
        data = ClipData.newPlainText("label", copyText);
        manager.setPrimaryClip(data);
        view.showTextCopied();
    }

    @Override
    public void copyLink() {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(CLIPBOARD_SERVICE);
        ClipData data = null;
        data = ClipData.newPlainText("text", bean.getUrl());
        manager.setPrimaryClip(data);
        view.showTextCopied();
    }

    @Override
    public void addToOrDeleteFromBookmarks() {
        if (queryIfIsBookmarked()) {
            //delete
            List<Collection> list = BaseApplication.getDaoSession().getCollectionDao().queryBuilder().where(CollectionDao.Properties.Title.eq(collection.getTitle())).list();
            DaoUtil.deleteCollection(context, list.get(0));
            view.showDeletedFromBookmarks();
        } else {
            //add
            DaoUtil.addCollection(context, collection);
            view.showAddedToBookmarks();
        }
    }

    @Override
    public boolean queryIfIsBookmarked() {
        List<Collection> list = BaseApplication.getDaoSession().getCollectionDao().queryBuilder().where(CollectionDao.Properties.Title.eq(bean.getTitle())).list();
        return list.size() != 0;
    }

    @Override
    public void requestData() {
        view.showCover(bean.getPic());
        view.setTitle(bean.getTitle());

        if ((context.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES) {//夜间模式
            view.showResult(setNightText(bean.getContent()));
        } else view.showResult(bean.getContent());
    }

    private String setNightText(String str) {
        StringBuilder builder = new StringBuilder();
        builder.append("<html><head><style type=\"text/css\">");
        builder.append("body{");
        //css控制字体颜色
        builder.append("color : #616161;");
        //css控制背景色
        builder.append("background : #212a2f");
        builder.append("}</style></head><body >");
        builder.append(str);
        builder.append("</body></html>");
        return builder.toString();
    }

    private boolean checkNull(String s) {
        return TextUtils.isEmpty(s);
    }
}

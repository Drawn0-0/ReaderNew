package com.example.acer.readernew.Activities.Detail;

import android.webkit.WebView;

import com.example.acer.readernew.BasePresenter;
import com.example.acer.readernew.BaseView;

/**
 * Created by acer on 2017/4/21.
 */

public class DetailContact {
    interface View extends BaseView<Presenter> {

        void showLoadingError();

        void showSharingError();

        void showResult(String result);

        void showResultWithoutBody(String url);

        void showCover(String url);

        void setTitle(String title);

        void setImageMode(boolean showImage);

        void showBrowserNotFoundError();

        void showTextCopied();

        void showCopyTextError();

        void showAddedToBookmarks();

        void showDeletedFromBookmarks();

    }

    interface Presenter extends BasePresenter {

        void openInBrowser();

        void shareAsText();

        void openUrl(WebView webView);

        void copyText();

        void copyLink();

        void addToOrDeleteFromBookmarks();

        boolean queryIfIsBookmarked();

        void requestData();

    }
}

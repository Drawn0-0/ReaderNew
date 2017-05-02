package com.example.acer.readernew.Activities.Detail;


import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.acer.readernew.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailFragment extends Fragment implements DetailContact.View {

    private AppBarLayout appBar;
    private CoordinatorLayout coordinatorLayout;
    private ImageView imageView;
    private NestedScrollView scrollView;
    private WebView webView;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private DetailContact.Presenter presenter;
    private FloatingActionButton floate;


    public DetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        initView(view);
        setHasOptionsMenu(true);
        presenter.requestData();
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.smoothScrollTo(0, 0);
            }
        });
        floate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.shareAsText();
            }
        });
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.detail_menu_more, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == android.R.id.home) {
            getActivity().onBackPressed();
        } else {
            if (itemId == R.id.detail_menu_more) {
                final BottomSheetDialog dialog = new BottomSheetDialog(getActivity());
                View view = getActivity().getLayoutInflater().inflate(R.layout.reading_actions_sheet, null);
                if (presenter.queryIfIsBookmarked()) {
                    ((TextView) view.findViewById(R.id.textView)).setText(R.string.cancel_bookmarks);
                }

                view.findViewById(R.id.detail_menu_bookmark).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.addToOrDeleteFromBookmarks();
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.layout_copy_link).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.copyLink();
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.layout_open_in_browser).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.openUrl(webView);
                        dialog.dismiss();
                    }
                });
                view.findViewById(R.id.layout_copy_text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.copyText();
                        dialog.dismiss();
                    }
                });
                dialog.setContentView(view);
                dialog.show();
            }
        }
        return true;
    }

    @Override
    public void setPresenter(DetailContact.Presenter presenter) {
        if (presenter != null) {
            this.presenter = presenter;
        }
    }

    @Override
    public void initView(View view) {
        appBar = (AppBarLayout) view.findViewById(R.id.detail_app_bar);
        coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.detail_coordinatorLayout);
        imageView = (ImageView) view.findViewById(R.id.detail_image_view);
        scrollView = (NestedScrollView) view.findViewById(R.id.detail_scrollView);
        webView = (WebView) view.findViewById(R.id.detail_web_view);

        webView.getSettings().setJavaScriptEnabled(true);
        //缩放,设置为不能缩放可以防止页面上出现放大和缩小的图标
        webView.getSettings().setBuiltInZoomControls(false);
        //缓存
        webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        //开启DOM storage API功能
        webView.getSettings().setDomStorageEnabled(true);
        //开启application Cache功能
        webView.getSettings().setAppCacheEnabled(false);

        toolbar = (Toolbar) view.findViewById(R.id.detail_toolbar);
        DetailActivity activity = (DetailActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.detail_toolbar_layout);
        floate = (FloatingActionButton) view.findViewById(R.id.floatingButton);
    }


    @Override
    public void showLoadingError() {

    }

    @Override
    public void showSharingError() {

    }

    @Override
    public void showResult(String result) {
        webView.loadDataWithBaseURL("x-data://base", result, "text/html", "utf-8", null);
    }

    @Override
    public void showResultWithoutBody(String url) {

    }

    @Override
    public void showCover(String url) {
        Glide.with(getContext())
                .load(url)
                .asBitmap()
                .placeholder(R.drawable.detail_holderplace)
                .error(R.drawable.detail_holderplace)
                .into(imageView);

    }

    @Override
    public void setTitle(String title) {
        collapsingToolbarLayout.setTitle(title);
    }

    @Override
    public void setImageMode(boolean showImage) {

    }

    @Override
    public void showBrowserNotFoundError() {

    }

    @Override
    public void showTextCopied() {
        Snackbar.make(imageView, R.string.copy_success, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showCopyTextError() {

    }

    @Override
    public void showAddedToBookmarks() {
        Snackbar.make(imageView, R.string.add_to_favorite, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showDeletedFromBookmarks() {
        Snackbar.make(imageView, R.string.delete_to_favorite, Snackbar.LENGTH_SHORT).show();
    }
}

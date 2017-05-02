package com.example.acer.readernew.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.acer.readernew.Activities.Detail.DetailActivity;
import com.example.acer.readernew.Adapter.CardRecycleViewCollect;
import com.example.acer.readernew.Adapter.CardRecycleViewHistory;
import com.example.acer.readernew.Bean.Collection;
import com.example.acer.readernew.Bean.history;
import com.example.acer.readernew.Interface.OnLongCllickListener;
import com.example.acer.readernew.Interface.RecycleViewOnClickListener;
import com.example.acer.readernew.R;
import com.example.acer.readernew.Utils.BaseApplication;
import com.example.acer.readernew.Utils.DaoUtil;
import com.example.acer.readernew.Utils.DefaultArgue;

import java.util.ArrayList;
import java.util.List;

public class HistoryActivity extends AppCompatActivity {

    private static final int LOADING = 0;
    private static final int FINISH = 1;
    private RecyclerView recyclerView;
    private ContentLoadingProgressBar progressBar;
    private List<history> collections = new ArrayList<>();
    private CardRecycleViewHistory adapter;
    private TextView tvData;
    private ContentObserver observer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle(R.string.history_title);
        }
        initView();
        initData();
        initEvent();
    }

    private void initEvent() {
        adapter.setOnClickListener(new RecycleViewOnClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                history itemData = adapter.getItemData(position);
                Intent intent = new Intent(HistoryActivity.this, DetailActivity.class);
                intent.putExtra("title", itemData.getTitle());
                intent.putExtra("time", itemData.getTime());
                intent.putExtra("src", itemData.getSrc());
                intent.putExtra("category", itemData.getCategory());
                intent.putExtra("url", itemData.getUrl());
                intent.putExtra("webUrl", itemData.getWeburl());
                intent.putExtra("pic", itemData.getPic());
                intent.putExtra("content", itemData.getContent());

                String transitionName = "shareView_image";
                Pair pairImg = new Pair<>(view.findViewById(R.id.iv_item_pic), transitionName);
                ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(HistoryActivity.this, pairImg);
                startActivity(intent, transitionActivityOptions.toBundle());
            }
        });

        adapter.setOnLongClickListener(new OnLongCllickListener() {
            @Override
            public void OnLongClick(View v, final int position) {
                AlertDialog.Builder builder = new AlertDialog.Builder(HistoryActivity.this);
                builder.setTitle(R.string.dialog_alert_title).setMessage(R.string.dialog_alert_content);
                builder.setPositiveButton(R.string.dialog_alert_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                        adapter.notifyItemRemoved(position);
                        BaseApplication.getDaoSession().getHistoryDao().delete(adapter.getItemData(position));
                        collections.remove(position);
                    }
                });
                builder.setNegativeButton(R.string.dialog_alert_cancel, null);
                builder.show();
            }
        });
        observer = new ContentObserver(new Handler()) {
            @Override
            public void onChange(boolean selfChange) {
                super.onChange(selfChange);
                collections.clear();
                initData();
            }
        };
        getContentResolver().registerContentObserver(DefaultArgue.uriCollection, true, observer);
    }

    private void initData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.obtainMessage(LOADING).sendToTarget();
                List<history> tempList = DaoUtil.getAllHistory();
                collections.addAll(tempList);
                handler.obtainMessage(FINISH).sendToTarget();
            }
        }).start();
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOADING:
                    recyclerView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    break;
                case FINISH:
                    progressBar.setVisibility(View.GONE);
                    if (collections.size() != 0) {
                        recyclerView.setVisibility(View.VISIBLE);
                        adapter.notifyDataSetChanged();
                    } else {
                        tvData.setVisibility(View.VISIBLE);
                    }
                    break;
            }

        }
    };

    private void initView() {
        tvData = (TextView) findViewById(R.id.tv_no_data);
        recyclerView = (RecyclerView) findViewById(R.id.collect_recycle);
        progressBar = (ContentLoadingProgressBar) findViewById(R.id.collect_progress);
        adapter = new CardRecycleViewHistory(this, collections);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        getContentResolver().unregisterContentObserver(observer);
        super.onDestroy();
    }
}

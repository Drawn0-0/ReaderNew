package com.example.acer.readernew.Activities.Detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.acer.readernew.Bean.NewsBean;
import com.example.acer.readernew.R;

public class DetailActivity extends AppCompatActivity {

    private DetailFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        //添加对转场动画的支持
//        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//        getWindow().setEnterTransition(new Explode().setDuration(2000));
//        getWindow().setExitTransition(new Explode().setDuration(2000));
        setContentView(R.layout.activity_detail);

        fragment = new DetailFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.detail_container, fragment)
                .commit();
//        }
        Intent intent = getIntent();

        NewsBean.ResultBean.ListBean bean = new NewsBean.ResultBean.ListBean();
        bean.setTitle(intent.getStringExtra("title"));
        bean.setCategory(intent.getStringExtra("category"));
        bean.setContent(intent.getStringExtra("content"));
        bean.setPic(intent.getStringExtra("pic"));
        bean.setSrc(intent.getStringExtra("src"));
        bean.setTime(intent.getStringExtra("time"));
        bean.setWeburl(intent.getStringExtra("webUrl"));
        bean.setUrl(intent.getStringExtra("url"));
        DetailPresenter presenter = new DetailPresenter(DetailActivity.this, fragment);
        presenter.setData(bean);
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
        super.onBackPressed();
    }
}

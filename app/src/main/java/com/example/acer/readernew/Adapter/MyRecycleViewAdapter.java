package com.example.acer.readernew.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.acer.readernew.Bean.NewsBean;
import com.example.acer.readernew.Interface.RecycleViewOnClickListener;
import com.example.acer.readernew.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acer on 2017/4/13.
 */

public class MyRecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private static final int TYPE_NORMAL = 0x00;
    private static final int TYPE_FOOTER = 0x02;
    private List<NewsBean.ResultBean.ListBean> data;
    private final Context context;
    private RecycleViewOnClickListener listener;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TYPE_NORMAL) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycleview, null);
            return new NormalHolder(view, listener);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_footer, parent, false);
            return new FooterHolder(view);
        }
    }

    public MyRecycleViewAdapter(Context context, List<NewsBean.ResultBean.ListBean> bean) {
        data = bean;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof NormalHolder) {

            //数据正常
            String picUrl = data.get(position).getPic();
            String title = data.get(position).getTitle();
            String src = data.get(position).getSrc();
            String time = data.get(position).getTime();
            if (!TextUtils.isEmpty(picUrl)) {
                //有图片
                Glide.with(context).load(picUrl).
                        asBitmap().
                        diskCacheStrategy(DiskCacheStrategy.ALL).
                        placeholder(R.drawable.loading_holderplace).
                        error(R.drawable.error).
                        into(((NormalHolder) holder).ivPic);
            }
            if (!TextUtils.isEmpty(src)) {
                ((NormalHolder) holder).tvSrc.setText(src);
            }
            if (!TextUtils.isEmpty(time)) {
                ((NormalHolder) holder).tvTime.setText(time);
            }
            ((NormalHolder) holder).tvTitle.setText(title);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == data.size())
            return TYPE_FOOTER;
        return TYPE_NORMAL;
    }

    public void setItemClickListener(RecycleViewOnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    public void setData(NewsBean data) {
        this.data.addAll(data.getResult().getList());
    }

    public List<NewsBean.ResultBean.ListBean> getData() {
        return data;
    }

    public static class NormalHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView tvTitle;
        private final TextView tvSrc;
        private final TextView tvTime;
        private final ImageView ivPic;
        private final RecycleViewOnClickListener listener;

        public NormalHolder(View itemView, RecycleViewOnClickListener listener) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
            tvSrc = (TextView) itemView.findViewById(R.id.tv_item_src);
            tvTime = (TextView) itemView.findViewById(R.id.tv_item_time);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_item_pic);
            itemView.setOnClickListener(this);
            this.listener = listener;
        }

        @Override
        public void onClick(View view) {
            if (listener != null) {
                listener.OnItemClick(view, getLayoutPosition());
            }
        }
    }

    public class FooterHolder extends RecyclerView.ViewHolder {
        public FooterHolder(View itemView) {
            super(itemView);
        }
    }
}

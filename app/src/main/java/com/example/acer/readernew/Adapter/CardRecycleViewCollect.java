package com.example.acer.readernew.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.acer.readernew.Bean.Collection;
import com.example.acer.readernew.Interface.OnLongCllickListener;
import com.example.acer.readernew.Interface.RecycleViewOnClickListener;
import com.example.acer.readernew.R;

import java.util.List;

/**
 * Created by acer on 2017/5/1.
 */

public class CardRecycleViewCollect extends RecyclerView.Adapter<CardRecycleViewCollect.VH> {
    private final Context context;
    private List<Collection> bean;
    private RecycleViewOnClickListener listener;
    private OnLongCllickListener longClickListener;

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);
        return new VH(view);
    }

    public CardRecycleViewCollect(Context context, List<Collection> bean) {
        this.context = context;
        this.bean = bean;
    }

    @Override
    public void onBindViewHolder(final VH holder, int position) {
        String title = bean.get(position).getTitle();
        String content = bean.get(position).getContent();
        content = Html.fromHtml(content).toString();
        String pic = bean.get(position).getPic();
        if (!TextUtils.isEmpty(pic)) {
            Glide.with(context).load(pic).
                    asBitmap().
                    diskCacheStrategy(DiskCacheStrategy.ALL).
                    placeholder(R.drawable.loading_holderplace).
                    error(R.drawable.error).
                    into((holder).ivPic);
        }
        holder.tvTitle.setText(title);
        holder.tvContent.setText(content);
        if (listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.OnItemClick(v, holder.getAdapterPosition());
                }
            });
        }
        if (longClickListener!=null){
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    longClickListener.OnLongClick(v,holder.getAdapterPosition());
                    return true;
                }
            });
        }
    }

    public Collection getItemData(int position) {
        return bean.get(position);
    }

    public void setOnClickListener(RecycleViewOnClickListener listener) {
        this.listener = listener;
    }
    public void setOnLongClickListener(OnLongCllickListener longClickListener){
        this.longClickListener = longClickListener;
    }
    @Override
    public int getItemCount() {
        return bean.size();
    }

    public static class VH extends RecyclerView.ViewHolder {

        private final TextView tvContent;
        private final TextView tvTitle;
        private final ImageView ivPic;

        public VH(View itemView) {
            super(itemView);
            ivPic = (ImageView) itemView.findViewById(R.id.iv_item_pic);
            tvContent = (TextView) itemView.findViewById(R.id.tv_item_content);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_title);
        }
    }
}

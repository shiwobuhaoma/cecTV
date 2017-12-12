package com.tv.cec.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tv.cec.bean.MusicBean;

import com.tv.cec.cectv.R;

import java.util.ArrayList;

import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Administrator on 2017/12/9.
 */

public class MusicPageAdapter extends RecyclerView.Adapter<MusicPageAdapter.MyViewHolder> {
    private ArrayList<MusicBean> mDatas;
    private Activity mContext;

    public MusicPageAdapter(Activity context, ArrayList<MusicBean> datas) {
        this.mContext = context;
        mDatas = datas;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.music_page, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        setValue(holder, position);
    }

    private void setValue(MyViewHolder holder, int position) {
        holder.music_player.setUp(mDatas.get(position).mp4Url
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, mDatas.get(position).title);
        Glide.with(mContext).load(mDatas.get(position).showImgUrl).into(holder.music_player.thumbImageView);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        JZVideoPlayerStandard music_player;


        public MyViewHolder(View view) {
            super(view);
            music_player = (JZVideoPlayerStandard) view.findViewById(R.id.music_player);

        }
    }
}

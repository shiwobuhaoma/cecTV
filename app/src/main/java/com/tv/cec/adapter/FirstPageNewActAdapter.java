package com.tv.cec.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.tv.cec.R;
import com.tv.cec.bean.NewAct;

import com.tv.cec.utils.GlideRoundTransform;

import java.util.ArrayList;

/**
 *
 * Created by Administrator on 2017/12/9.
 */

public class FirstPageNewActAdapter extends RecyclerView.Adapter<FirstPageNewActAdapter.MyViewHolder> {
    private  ArrayList<NewAct> mDatas;
    private Activity mContext;
    private OnItemClickLitener mOnItemClickLitener;
    public interface OnItemClickLitener
    {
        void onItemClick(View view, int position);
    }
    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener)
    {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
    public FirstPageNewActAdapter(Activity context, ArrayList<NewAct> datas) {
        this.mContext = context;
        mDatas = datas;
    }
    @Override
    public FirstPageNewActAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(
                mContext).inflate(R.layout.first_page_new_act, parent,
                false));
        return holder;
    }

    @Override
    public void onBindViewHolder(FirstPageNewActAdapter.MyViewHolder holder, int position) {
        setValue(holder, position);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }
    private void setValue(MyViewHolder holder, int position) {
        holder.title.setText(mDatas.get(position).titile);
        Glide.with(mContext).load(mDatas.get(position).url).transform(new GlideRoundTransform(mContext, 10)).into(holder.image);
        holder.context.setText(mDatas.get(position).context);
    }
    class MyViewHolder extends RecyclerView.ViewHolder
    {

        TextView title;
        TextView context;
        ImageView image;

        public MyViewHolder(View view)
        {
            super(view);
            title =(TextView) view.findViewById(R.id.title);
            context = (TextView) view.findViewById(R.id.context);
            image = (ImageView) view.findViewById(R.id.image);
        }
    }
}

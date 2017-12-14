package com.tv.cec.mvp.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.tv.cec.R;
import com.tv.cec.adapter.MusicPageAdapter;
import com.tv.cec.base.BaseFragmentLazyLoad;
import com.tv.cec.bean.MusicBean;

import java.util.ArrayList;
import java.util.Random;

import butterknife.BindView;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;

/**
 * 电影
 * Created by WangChang on 2016/5/15.
 */
public class TvFragment extends BaseFragmentLazyLoad {


    @BindView(R.id.musicPage_rv)
    RecyclerView musicPageRv;
    @BindView(R.id.music_spl)
    SwipeRefreshLayout musicSpl;
    private ArrayList<MusicBean> mData = new ArrayList<>();
    private String[] mp4Url = {"http://pgccdn.v.baidu.com/2955574219_2376051436_20170522152307.mp4?authorization=bce-auth-v1/c308a72e7b874edd9115e4614e1d62f6/2017-05-22T07:23:19Z/-1//610fae8140f60cbf1ca09d2a9d29f6a161177b8a0a7e2d334ebbcb228229c2f0&responseCacheControl=max-age=8640000&responseExpires=Wed,+30+Aug+2017+15:23:19+GMT&xcode=56f58c59f9489238f9398ccc6007401f4cd5c82b626a1857&time=1512904845&_=1512825075343",
            "http://vt1.doubanio.com/201712100003/4b3fc00e8d04dc459cf2d19803b7dee2/view/movie/M/302240803.mp4",
            "http://vt1.doubanio.com/201712100008/60d527c8ab379294df36c264b642f8ea/view/movie/M/302240321.mp4",
            "http://vt1.doubanio.com/201712100010/f280446bf8207a1f56c8558b2ac48aa7/view/movie/M/301190886.mp4",
            "http://vt1.doubanio.com/201712100013/0ba4e7dcbaeb77b5dd590f1fd4fd1df4/view/movie/M/301370586.mp4"};

    private String[] showImgUrl = {"http://t3.baidu.com/it/u=1288139589,2925788866&fm=20",
            "https://img1.doubanio.com/img/trailer/small/2507053509.jpg",
            "https://img3.doubanio.com/img/trailer/small/2506524226.jpg",
            "https://img3.doubanio.com/img/trailer/small/1668750491.jpg",
            "https://img3.doubanio.com/img/trailer/small/2024079595.jpg"
    };
    private String[] title = {"大话西游", "帕丁顿熊2","至爱梵高·星空之谜","天空之城","哈尔的移动城堡"};
    private MusicPageAdapter mpa;


    @Override
    protected void setStatusBarHeight() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tv;
    }

    public void initView() {

        mpa = new MusicPageAdapter(getActivity(), mData);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        musicPageRv.setLayoutManager(llm);
        musicPageRv.setLayoutManager(llm);
        musicPageRv.setHasFixedSize(true);
        musicPageRv.setNestedScrollingEnabled(false);
//        musicPageRv.addItemDecoration(new DividerItemDecoration(getActivity()));
        musicPageRv.setAdapter(mpa);
        musicPageRv.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {
//                JZVideoPlayer.onChildViewAttachedToWindow(view,R.id.music_player);
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                JZVideoPlayer jzvd = (JZVideoPlayer) view.findViewById(R.id.music_player);
                if (jzvd != null && JZUtils.dataSourceObjectsContainsUri(jzvd.dataSourceObjects, JZMediaManager.getCurrentDataSource())) {
                    JZVideoPlayer.releaseAllVideos();
                }
//                JZVideoPlayer.onChildViewDetachedFromWindow(view);
            }
        });
        musicSpl.setProgressBackgroundColorSchemeResource(android.R.color.white);
        musicSpl.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        musicSpl.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        musicSpl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                musicSpl.setRefreshing(true);
                final Random random = new Random();

                // 这里是主线程
                // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int suiji = random.nextInt(5);
                        if (suiji >= 0 && suiji <= 4) {
                            mData.add(0, new MusicBean(mp4Url[suiji], showImgUrl[suiji], title[suiji]));
                        }
                        mpa.notifyDataSetChanged();
                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        musicSpl.setRefreshing(false);
                    }
                }, 1200);
            }
        });
    }

    @Override
    protected void initData() {
        mData = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            mData.add(new MusicBean(mp4Url[i], showImgUrl[i], title[i]));
        }
    }

    public static TvFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        TvFragment fragment = new TvFragment();
        fragment.setArguments(args);
        return fragment;
    }


}

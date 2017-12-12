package com.tv.cec.mvp.fragment;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.tv.cec.adapter.FixPagerAdapter;
import com.tv.cec.base.BaseFragmentLazyLoad;
import com.tv.cec.cectv.R;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 首页
 * Created by WangChang on 2016/5/15.
 */
public class HomeFragment extends BaseFragmentLazyLoad {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    @BindView(R.id.timeline_viewpager)
    ViewPager timelineViewpager;
    private static String[] tabs = {"资讯", "直播", "栏目设置", "节目单", "展映展播", "活动资讯"};
    @BindView(R.id.app_bar)
    AppBarLayout flToolbar;
    @BindView(R.id.fab_download)
    FloatingActionButton fabDownload;


    @Override
    protected void setStatusBarHeight() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    protected void initView() {
        for (int i = 0; i < tabs.length; i++) {
            tabLayout.addTab(tabLayout.newTab().setText(tabs[i]));
        }
        //        tabLayout.getTabAt(2).select();//默认选中某项

        FixPagerAdapter spa = new FixPagerAdapter(getFragmentManager());
        spa.setFragments(getFragments());
        spa.setTitles(tabs);
        timelineViewpager.setAdapter(spa);
        tabLayout.setupWithViewPager(timelineViewpager);
        flToolbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    fabDownload.show();
                } else {
                    fabDownload.hide();

                }
            }
        });
        fabDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    protected void initData() {

    }


    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
//        for (int i = 0; i < tabs.length; i++) {
        fragments.add(new InformationFragment());
        fragments.add(new LiveBroadcastFragment());
        fragments.add(new TvFragment());
        fragments.add(new InformationFragment());
        fragments.add(new InformationFragment());
        fragments.add(new InformationFragment());
//        }

        return fragments;
    }

    public static HomeFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }



}

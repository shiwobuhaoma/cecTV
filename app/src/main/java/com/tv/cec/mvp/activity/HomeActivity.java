package com.tv.cec.mvp.activity;

import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.widget.LinearLayout;

import com.tv.cec.R;
import com.tv.cec.adapter.FixPagerAdapter;
import com.tv.cec.base.BaseActivity;
import com.tv.cec.base.BaseFragment;
import com.tv.cec.factory.FragmentFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by Administrator on 2017/12/6.
 */

public class HomeActivity extends BaseActivity {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout ;
    @BindView(R.id.main_viewpager)
    ViewPager mainViewPager ;
    @BindView(R.id.status_bar)
    LinearLayout statusBar ;

    private FixPagerAdapter fixPagerAdapter ;
    private String[] titles = {"首页","资讯","直播","节目单","我的"};//"栏目设置",,"展映展播","活动资讯"
    private List<Fragment> fragments ;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initView() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            final int statusHeight = getStatusBarHeight();
            statusBar.post(new Runnable() {
                @Override
                public void run() {
                    int titleHeight = statusBar.getHeight();
                    android.widget.LinearLayout.LayoutParams params = (android.widget.LinearLayout.LayoutParams) statusBar.getLayoutParams();
                    params.height = statusHeight + titleHeight;
                    statusBar.setLayoutParams(params);
                }
            });
        }

        initViewPagerFragment();
    }


    private void initViewPagerFragment() {
        fixPagerAdapter = new FixPagerAdapter(getSupportFragmentManager()) ;

        fragments = new ArrayList<>() ;
        for(int i = 0 ;i < titles.length ; i ++){
            fragments.add(FragmentFactory.createFragment(i)) ;
        }
        fixPagerAdapter.setTitles(titles);
        fixPagerAdapter.setFragments(fragments);

        mainViewPager.setAdapter(fixPagerAdapter);
        tabLayout.setupWithViewPager(mainViewPager);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);

        mainViewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                Fragment fragment = FragmentFactory.createFragment(position);
//                fragment.show();

            }
        });
    }

}

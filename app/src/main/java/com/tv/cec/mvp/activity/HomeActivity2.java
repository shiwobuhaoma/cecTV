package com.tv.cec.mvp.activity;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.ImageView;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.tv.cec.adapter.SeclectPageAdapter;
import com.tv.cec.base.BaseActivity;
import com.tv.cec.cectv.R;
import com.tv.cec.mvp.fragment.BookFragment;
import com.tv.cec.mvp.fragment.HomeFragment;
import com.tv.cec.mvp.fragment.MusicFragment;
import com.tv.cec.mvp.fragment.MyFragment;

import java.util.ArrayList;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;

/**
 *
 * Created by Administrator on 2017/12/8.
 */

public class HomeActivity2 extends BaseActivity {
    @BindView(R.id.layFrame)
    ViewPager viewPager;
//    FrameLayout layFrame;

    @BindView(R.id.bottom_navigation_bar)
    BottomNavigationBar bottomNavigationBar;
    @BindView(R.id.nv_menu)
    NavigationView nvMenu;
    @BindView(R.id.dl_root)
    DrawerLayout dlRoot;
    private ArrayList<Fragment> fragments;
    private HomeFragment home;
    private BookFragment books;
    private MusicFragment music;
    private MusicFragment tv;
    private MyFragment my;
    private SeclectPageAdapter spa;
    private ImageView mivMenu;
    private ImageView civHead;

    @Override
    protected void initLayout() {
        initTheme();
        setContentView(R.layout.activity_home2);
    }

    private void initTheme() {
    }

    @Override
    protected void initView() {

        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED);
        bottomNavigationBar
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC
                );
        bottomNavigationBar.addItem(new BottomNavigationItem(R.mipmap.ic_home_white_24dp, "Home").setActiveColorResource(R.color.orange))
                .addItem(new BottomNavigationItem(R.mipmap.ic_book_white_24dp, "Books").setActiveColorResource(R.color.teal))
                .addItem(new BottomNavigationItem(R.mipmap.ic_music_note_white_24dp, "Music").setActiveColorResource(R.color.blue))
                .addItem(new BottomNavigationItem(R.mipmap.ic_tv_white_24dp, "Movies & TV").setActiveColorResource(R.color.brown))
                .addItem(new BottomNavigationItem(R.mipmap.personal, "Me").setActiveColorResource(R.color.grey))
                .setFirstSelectedPosition(0)
                .initialise();

        fragments = getFragments();
        setDefaultFragment();
        bottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.SimpleOnTabSelectedListener(){
            @Override
            public void onTabSelected(int position) {
                viewPager.setCurrentItem(position);
            }
        });
        mivMenu = (ImageView) nvMenu.getHeaderView(0).findViewById(R.id.miv_menu);

        civHead = (ImageView) nvMenu.getHeaderView(0).findViewById(R.id.civ_head);
        
//        Glide.with(this).load(R.drawable.touxiang).into(civHead);
        civHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dlRoot.closeDrawer(GravityCompat.START);
                bottomNavigationBar.selectTab(4);
            }
        });

    }

    /**
     * 设置默认的
     */
    private void setDefaultFragment() {
        spa = new SeclectPageAdapter(getSupportFragmentManager(), fragments);
        viewPager.setAdapter(spa);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            @Override
            public void onPageSelected(int position) {
                bottomNavigationBar.selectTab(position);
            }
        });
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(5);
    }

    private ArrayList<Fragment> getFragments() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        home = HomeFragment.newInstance("Home");
        books = BookFragment.newInstance("Books");
        music = MusicFragment.newInstance("Music");
        tv = MusicFragment.newInstance("Movies & TV");
        my = new MyFragment();
        fragments.add(home);
        fragments.add(books);
        fragments.add(music);
        fragments.add(tv);
        fragments.add(my);
        return fragments;
    }



    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }
}

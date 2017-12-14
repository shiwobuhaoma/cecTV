package com.tv.cec.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tv.cec.R;
import com.tv.cec.adapter.FixPagerAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 书
 * Created by WangChang on 2016/5/15.
 */
public class BookFragment extends Fragment {
    private static String[] tabs = {"文学", "文化", "生活"};
    @BindView(R.id.tl_tabs)
    TabLayout tlTabs;
    @BindView(R.id.vp_fragment)
    ViewPager vpFragment;
    Unbinder unbinder;
    private ArrayList<Fragment> fragments;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_book, null);
        unbinder = ButterKnife.bind(this, view);
        fragments = new ArrayList<>();
        initData();
        return view;
    }

    private void initData() {
        for (int i = 0; i < tabs.length; i++) {
            tlTabs.addTab(tlTabs.newTab().setText(tabs[i]));
            fragments.add(GameFragment.newInstance(tabs[i]));
        }
        //要设置到viewpager.setAdapter后才起作用
        FixPagerAdapter spa = new FixPagerAdapter(getFragmentManager());
        spa.setFragments(fragments);
        spa.setTitles(tabs);
        vpFragment.setAdapter(spa);
        tlTabs.setupWithViewPager(vpFragment);
        tlTabs.setVerticalScrollbarPosition(0);
    }


    public static BookFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        BookFragment fragment = new BookFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}

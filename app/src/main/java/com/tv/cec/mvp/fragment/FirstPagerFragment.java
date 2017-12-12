package com.tv.cec.mvp.fragment;

import android.os.Bundle;
import android.view.View;

import com.tv.cec.base.BaseFragment;
import com.tv.cec.base.BaseFragmentLazyLoad;
import com.tv.cec.cectv.R;
import com.tv.cec.utils.UIUtils;

import butterknife.ButterKnife;

/**
 * 首页
 * @author lxf
 */

public class FirstPagerFragment extends BaseFragmentLazyLoad {


    public static FirstPagerFragment newInstance(String content) {
        Bundle args = new Bundle();
        args.putString("ARGS", content);
        FirstPagerFragment fragment = new FirstPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void setStatusBarHeight() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_firstpage;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }
}

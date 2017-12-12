package com.tv.cec.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tv.cec.mvpbase.BaseView;
import com.tv.cec.view.LoadingPager;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public abstract class BaseFragment extends Fragment implements BaseView {

    private LoadingPager pager ;
    protected BaseActivity mActivity ;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        pager = new LoadingPager(getContext()) {
            @Override
            protected View createLoadedView() {
                return BaseFragment.this.createSuccessView();
            }

            @Override
            protected void load() {
                BaseFragment.this.load();
            }
        };

        return pager;
    }

    public void show(){
        if(pager != null)
            pager.show();
    }

    public void setState(LoadingPager.LoadResult result){
        if(pager != null){
            pager.setState(result);
        }
    }

    @Override
    public void shotToast(String msg) {
        mActivity.shotToast(msg);
    }

    public abstract View createSuccessView() ;
    public abstract void load();
}

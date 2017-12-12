package com.tv.cec.mvp.presenter;

import com.tv.cec.bean.User;
import com.tv.cec.mvp.view.LoginView;
import com.tv.cec.mvpbase.BasePresenter;

/**
 * 登陆主持
 * Created by Administrator on 2017/12/7.
 */

public interface LoginPresenter extends BasePresenter<LoginView> {
    void login(User user);
}

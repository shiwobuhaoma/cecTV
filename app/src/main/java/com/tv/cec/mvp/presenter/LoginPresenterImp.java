package com.tv.cec.mvp.presenter;

import com.tv.cec.bean.User;
import com.tv.cec.mvp.view.LoginView;
import com.tv.cec.mvpbase.BasePresenterImpl;

/**
 * 登陆主持实现类，BasePresenterImpl主持基类，实现和view的绑定和解绑；
 * LoginPresenter登陆主持接口，实现登陆业务逻辑
 * Created by Administrator on 2017/12/7.
 */

public class LoginPresenterImp extends BasePresenterImpl<LoginView> implements LoginPresenter{


    @Override
    public void login(User user) {
        if (user.userName.equals("abc")&& user.userPwd.equals("123")){
            mPresenterView.loginSuccess();
        }else{
            mPresenterView.loginFaile();
        }
    }
}

package com.tv.cec.mvp.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.tv.cec.base.BaseActivity;
import com.tv.cec.bean.User;
import com.tv.cec.cectv.R;
import com.tv.cec.mvp.presenter.LoginPresenterImp;
import com.tv.cec.mvp.view.LoginView;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/12/7.
 */

public class LoginActivity extends BaseActivity implements LoginView{
    @BindView(R.id.user_name)
    EditText userName;
    @BindView(R.id.user_pwd)
    EditText userPwd;
    @BindView(R.id.login)
    Button login;
    private LoginPresenterImp loginPresenterImp;

    @Override
    protected void initLayout() {
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initView() {

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenterImp = new LoginPresenterImp();
                String user_name = userName.getText().toString();
                String user_pwd = userPwd.getText().toString();
                User user = new User();
                user.userName=user_name;
                user.userPwd= user_pwd;
                loginPresenterImp.login(user);
            }
        });
    }

    @Override
    public void loginSuccess() {
        Toast.makeText(this,"登陆成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void loginFaile() {
        Toast.makeText(this,"登陆失败",Toast.LENGTH_LONG).show();
    }
}

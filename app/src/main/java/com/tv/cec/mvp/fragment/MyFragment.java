package com.tv.cec.mvp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.tv.cec.bean.MyGvBean;
import com.tv.cec.cectv.R;
import com.tv.cec.mvp.activity.LoginActivity;
import com.tv.cec.utils.UIUtils;
import com.tv.cec.view.widget.MyEnterLayout;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 我的
 *
 * @author lxf
 */

public class MyFragment extends Fragment {
    @BindView(R.id.fl_my_toolbar)
    FrameLayout flToolbar;
    @BindView(R.id.book_game_layout)
    MyEnterLayout book_game_layout ;
    @BindView(R.id.buy_layout)
    MyEnterLayout buy_layout ;
    @BindView(R.id.huaban_layout)
    MyEnterLayout huaban_layout ;
    @BindView(R.id.setting_computer)
    MyEnterLayout setting_computer ;
    @BindView(R.id.faq_layout)
    MyEnterLayout faq_layout ;
    @BindView(R.id.check_update_layout)
    MyEnterLayout check_update_layout ;
    @BindView(R.id.about_layout)
    MyEnterLayout about_layout ;

    private List<MyGvBean> gvBeanList = new ArrayList<>() ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate( R.layout.fragment_my,null);
        ButterKnife.bind(this,view);
        initView();
        return view;
    }

    protected void initView() {
//        MySubAdapter adapter = new MySubAdapter(getContext(),gvBeanList) ;

        book_game_layout.setTitle(UIUtils.getString(R.string.follow));
        book_game_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(getActivity(), LoginActivity.class));
                Toast.makeText(getActivity(),"关注",Toast.LENGTH_LONG).show();
            }
        });
        buy_layout.setTitle(UIUtils.getString(R.string.collection));
        huaban_layout.setTitle(UIUtils.getString(R.string.mine_message));
        setting_computer.setTitle(UIUtils.getString(R.string.action_settings));
        faq_layout.setTitle(UIUtils.getString(R.string.menu_feedback));
        check_update_layout.setTitle(UIUtils.getString(R.string.settings_check_version_update));
        about_layout.setTitle(UIUtils.getString(R.string.about));
    }


    protected void initData() {
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_prize),R.drawable.icon_market_lucky_draw));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_gift),R.drawable.ic_mine_package_normal));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.appzone_comments),R.drawable.icon_market_comment));
        gvBeanList.add(new MyGvBean(UIUtils.getString(R.string.market_mine_message),R.drawable.icon_market_message));

    }

    protected int getStatusBarHeight() {
        try {
            Class<?> c = Class.forName("com.android.internal.R$dimen");
            Object obj = c.newInstance();
            Field field = c.getField("status_bar_height");
            int x = Integer.parseInt(field.get(obj).toString());
            return getResources().getDimensionPixelSize(x);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}

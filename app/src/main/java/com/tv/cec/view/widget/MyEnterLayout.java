package com.tv.cec.view.widget;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tv.cec.cectv.R;
import com.tv.cec.utils.DensityUtil;


public class MyEnterLayout extends LinearLayout {
    private TextView setItemTitle;
    private CardView item_layout;

    public MyEnterLayout(Context paramContext) {
        super(paramContext);
        init(paramContext);
    }

    public MyEnterLayout(Context paramContext, AttributeSet paramAttributeSet) {
        super(paramContext, paramAttributeSet);
        init(paramContext);
    }

    private void init(Context context) {
        View view = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.settings_my_normal_item, this);
        item_layout = ((CardView) view.findViewById(R.id.item_layout));
        setItemTitle = ((TextView) item_layout.findViewById(R.id.setItemTitle));
    }

    public void setTitle(String title) {
        if(!TextUtils.isEmpty(title))
            setItemTitle.setText(title);
    }
}
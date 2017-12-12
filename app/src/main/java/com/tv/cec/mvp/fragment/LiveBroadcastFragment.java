package com.tv.cec.mvp.fragment;

import com.bumptech.glide.Glide;
import com.tv.cec.base.BaseFragmentLazyLoad;
import com.tv.cec.cectv.R;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * 直播页
 *
 * @author lxf
 */

public class LiveBroadcastFragment extends BaseFragmentLazyLoad {
    @BindView(R.id.videoplayer)
    JZVideoPlayerStandard videoPlayer;


    @Override
    protected void setStatusBarHeight() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_live_broadcast;
    }

    public void initView() {
        videoPlayer.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");
        Glide.with(getActivity()).load("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640").into(videoPlayer.thumbImageView);
    }

    @Override
    protected void initData() {

    }


}

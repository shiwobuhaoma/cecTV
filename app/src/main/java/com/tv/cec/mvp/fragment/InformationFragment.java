package com.tv.cec.mvp.fragment;

import android.os.Handler;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.tv.cec.adapter.FirstPageNewActAdapter;
import com.tv.cec.base.BaseFragmentLazyLoad;
import com.tv.cec.bean.NewAct;
import com.tv.cec.cectv.R;
import com.tv.cec.utils.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;

/**
 * 资讯页
 *
 * @author lxf
 */

public class InformationFragment extends BaseFragmentLazyLoad {
    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.firstPage_rv)
    RecyclerView firstPageRv;
    @BindView(R.id.firstPage_nsv)
    NestedScrollView firstPageNsv;
    @BindView(R.id.firstPage_spl)
    SwipeRefreshLayout firstPageSpl;
    private ArrayList<String> urlImages = new ArrayList<>();
    private List<String> titles = new ArrayList<>();
    private ArrayList<NewAct> datas = new ArrayList<>();
    private String[] images = {"http://www.cec.tv/Uploads/news/2017-04-06/58e5efa01cb2c.jpg?size=1080x540",
            "http://www.cec.tv/Uploads/news/2017-04-06/58e5efdb01de4.jpg?size=1080x540",
            "http://www.cec.tv/Uploads/vod/2017-05-16/591aa265b8672.png",
            "http://www.cec.tv/Uploads/special/2017-03-16/58c9f9d3e928d.jpg?size=1080x540"};
    private String[] title = {"“少年中国·国际文化小使者”发布会在京举行",
            "同根同梦·2017全球华人春节联欢晚会在CECTV播出",
            "＂北京之夜＂绽放迪拜", "【魅力活动】魅力新星艺术素质教育系列活动"};
    private String[] contexts = {"中国国际教育电视台讯：（记者石竣予） 2017年3月26日，“少年中国·国际文化小使者”发布会在北京人民日报社新媒体大厦1号演播厅举行。主办单位人民网代表、中国文化旅游摄影协会代表，及承办单位北京市随和文化发…",
            "上图： 同根同梦.2017全球华人春节联欢晚会 中国国际教育电视台讯：同根同梦.2017全球华人春节联欢晚会于1月27日在中国国际教育电视台汉语文化频道播出。本次晚会以“同系血脉亲情，共铸中国梦”为主题，邀请…",
            "中国国际教育电视台讯：3月25日晚，＂迪拜龙城－北京之夜＂大型慰侨文艺演出晚会在阿联酋迪拜圆满落幕，来自北京的表演艺术家们为现场1000多名来自世界各地观众和阿联酋华侨华人带来一场精神艺术上的饕餮盛宴…",
            "活动介绍 序言 为落实教育部《关于推进学校艺术教育发展的若干意见》，要求对中小学校和中等职业学校学生进行艺术素质测评，测评结果作为中高考录取的参考依据意见，要建立中小学学生艺术素质评价制度，鼓励…"};
    private FirstPageNewActAdapter fpnaa;


    @Override
    protected void setStatusBarHeight() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_firstpage;
    }

    public void initView() {


        banner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE)
                .setImageLoader(new GlideImageLoader())
                .setImages(urlImages)
                .setBannerAnimation(Transformer.DepthPage)
                .setBannerTitles(titles)
                .setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .isAutoPlay(true)
                .setDelayTime(3000)
                .setIndicatorGravity(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE)
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {

                    }
                }).start();
        for (int i = 0; i < title.length; i++) {
            datas.add(new NewAct(images[i], title[i], contexts[i]));
        }
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setSmoothScrollbarEnabled(true);
        layoutManager.setAutoMeasureEnabled(true);
        firstPageRv.setLayoutManager(layoutManager);
        firstPageRv.setHasFixedSize(true);
        firstPageRv.setNestedScrollingEnabled(false);
//        firstPageRv.addItemDecoration(new DividerItemDecoration(getActivity()));
        fpnaa = new FirstPageNewActAdapter(getActivity(), datas);
        firstPageRv.setAdapter(fpnaa);
        fpnaa.setOnItemClickLitener(new FirstPageNewActAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
        firstPageSpl.setProgressBackgroundColorSchemeResource(android.R.color.white);
        firstPageSpl.setColorSchemeResources(android.R.color.holo_blue_light,
                android.R.color.holo_red_light,android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        firstPageSpl.setProgressViewOffset(false, 0, (int) TypedValue
                .applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources()
                        .getDisplayMetrics()));
        firstPageSpl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 开始刷新，设置当前为刷新状态
                firstPageSpl.setRefreshing(true);
                final Random random = new Random();

                // 这里是主线程
                // 一些比较耗时的操作，比如联网获取数据，需要放到子线程去执行
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int suiji = random.nextInt(5);
                        if (suiji >= 0 && suiji < 4) {
                            datas.add(0, new NewAct(images[suiji], title[suiji], contexts[suiji]));
                        }
                        fpnaa.notifyDataSetChanged();
                        // 加载完数据设置为不刷新状态，将下拉进度收起来
                        firstPageSpl.setRefreshing(false);
                    }
                }, 1200);

            }
        });
    }

    @Override
    protected void initData() {
        urlImages.add("http://www.cec.tv/Uploads/slide/2017-08-07/5987e8ef51373.JPG");
        urlImages.add("http://www.cec.tv/Uploads/slide/2017-08-07/5987e8a0cb729.JPG");
        urlImages.add("http://www.cec.tv/Uploads/slide/2017-04-27/590189a398b68.jpg");
        urlImages.add("http://www.cec.tv/Uploads/slide/2017-04-27/59018793360c8.jpg");
        urlImages.add("http://www.cec.tv/Uploads/slide/2017-04-07/58e78e7347356.png");
        titles.add("庆建军，军中百灵张华敏放声高歌古长城");
        titles.add("“放歌长城·祝福祖国”系列艺术展演盛典在响水湖成功举办");
        titles.add("【魅力活动】魅力新星艺术素质教育系列活动");
        titles.add("丝路大使谈丝路");
        titles.add("少年志");
    }


    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
        banner.stopAutoPlay();
    }



}

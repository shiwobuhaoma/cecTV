package com.tv.cec.mvp.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.text.TextUtils;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.tv.cec.base.BaseActivity;
import com.tv.cec.cectv.R;
import com.tv.cec.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by lizetong on 2017/5/6.
 */

public class WebViewActivity extends BaseActivity {

    private final static String KEY_TITLE = "web_view_title";
    private final static String KEY_URL = "web_view_url";



    private String mTitle;
    private String mUrl;

    //    BridgeModel bridgeModel;
    private BottomSheetDialog mSheetDialog;
    private String mImagePath;
    private String imgUrl;
    private Bitmap mBitmap;

    public static void launch(Context context, String url) {
        launch(context, null, url);
    }

    public static void launch(Context context, String title, String url) {
        if (TextUtils.isEmpty(url)) {
            ToastUtils.showToast("跳转链接为空");
            return;
        }
        Uri uri = Uri.parse(url);
        String scheme = uri.getScheme();
        if (TextUtils.isEmpty(scheme) || !scheme.contains("http")) {
            return;
        }
        Intent intent = new Intent(context, WebViewActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtra(KEY_TITLE, title);
        intent.putExtra(KEY_URL, url);
        context.startActivity(intent);
    }


    @Override
    protected void initLayout() {
        setContentView(R.layout.lac_webview_layout);
        ButterKnife.bind(this);
    }

    @Override
    protected void initView() {
        initData();
    }

    protected void initData() {
//        setWebViewOption();
//        setJsBridgeOption();
        mTitle = getIntent().getStringExtra(KEY_TITLE);
        mUrl = getIntent().getStringExtra(KEY_URL);
        if (!TextUtils.isEmpty(mTitle)) {
//            setBarTitle(mTitle);
//            hideBarTitle(false);
        } else {
//            hideBarTitle(true);
        }
//        if (!TextUtils.isEmpty(mUrl) && webview != null) {
//            loadUrl(mUrl);
//        }
//        titleBar.getBack().setOnClickListener(v -> {
//            if (webview.canGoBack()) {
//                webview.goBack();
//            } else {
//                finishActivity();
//            }
//        });
    }

 /*   private void setJsBridgeOption() {
        webview.registerHandler("share", new BridgeHandler() {
            @Override
            public void handler(String data, CallBackFunction function) {
                try {
                    ShareModel shareModel = (ShareModel) JsonUtil.jsonToBean(data, ShareModel.class);
                    if (shareModel != null) {
                        if (!TextUtils.isEmpty(shareModel.getImgUrl())) {
                            Glide.with(WebViewActivity.this).load(shareModel.getImgUrl()).asBitmap().into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                                    mBitmap = resource;
                                }
                            });
                        }
                        showSheetDailog(shareModel);
                    }
                } catch (Exception e) {

                }
            }
        });


        titleBar.getRightText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bridgeModel != null && !TextUtils.isEmpty(bridgeModel.getAction()) && !TextUtils.isEmpty(bridgeModel.getUrl())) {
                    webview.callHandler(bridgeModel.getAction(), bridgeModel.getUrl(), new CallBackFunction() {
                        @Override
                        public void onCallBack(String data) {

                        }
                    });

                }
            }
        });
    }

    private void setWebViewOption() {
        WebSettings settings = webview.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        settings.setDomStorageEnabled(true);
        settings.setBlockNetworkImage(false);
        // settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        settings.setUseWideViewPort(true);
        settings.setLoadWithOverviewMode(true);
        webview.setWebViewClient(new MyWebViewClient(webview));
        webview.setWebChromeClient(new MyWebChromeClient());
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
    }

    private void loadUrl(String url) {
        if (url == null || webview == null) return;
        if (!url.contains("leaicai")) {
            webview.loadUrl(url);
        } else {
            TreeMap<String, String> treeMap = new TreeMap<>();
            treeMap.put(ParamsMap.KEY_SESSION, Global.getSessionId());
            webview.loadUrl(url, treeMap);
        }
    }
*/

    public boolean parseScheme(String url) {
        if (url.contains("platformapi/startapp")) {
            return true;
        } else if ((Build.VERSION.SDK_INT > Build.VERSION_CODES.M)
                && (url.contains("platformapi") && url.contains("startapp"))) {
            return true;
        } else {
            return false;
        }
    }


 /*   private class MyWebViewClient extends BridgeWebViewClient {
        public MyWebViewClient(BridgeWebView webView) {
            super(webView);
        }

        //忽略https验证
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {

            if (parseScheme(url)) {
                //支付宝白名单
                try {
                    Intent intent = Intent.parseUri(url, Intent.URI_INTENT_SCHEME);
                    intent.addCategory("android.intent.category.BROWSABLE");
                    intent.setComponent(null);
                    startActivity(intent);
                    finishActivity();
                } catch (Exception e) {

                }
                return true;
            } else if (url.startsWith("weixin:")) {
                //微信白名单
                try {
                    startActivity(new Intent("android.intent.action.VIEW", Uri.parse(url)));
                    finishActivity();
                } catch (Exception e) {

                }
                return true;
            }

            if (url.contains("intent")) {
                JumpHelper.jumpByUri(WebViewActivity.this, url);
                finishActivity();
            } else if (url.startsWith("http")) {
                if (!TextUtils.isEmpty(url) && webview != null) {
                    loadUrl(url);
                }
            } else {
                return super.shouldOverrideUrlLoading(webView, url);
            }
            return true;
        }



       *//* @Override
        //   @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
            if (!Global.isAgent() || !url.contains("http") || !url.contains("leaicai"))
                return super.shouldInterceptRequest(view, url);
            try {
                OkHttpClient httpClient = OkHttpUtils.getInstance();
                Request OkHttpRequest = new Request.Builder()
                        .url(url)
                        .addHeader(ParamsMap.KEY_SESSION, Global.getSessionId())
                        .build();
                Response response = httpClient.newCall(OkHttpRequest).execute();
                return new WebResourceResponse(getMimeType(url), response.header("content-encoding", "utf-8"), response.body().byteStream());
            } catch (IOException e) {
                return null;
            }
        }*//*
    }


    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            LacLog.v("webview", "newProgress:" + newProgress);
            if (newProgress > 25) {
                //injectJS(view);
            }
            if (mProgressBar != null) {
                if (newProgress == 100) {
                    mProgressBar.setVisibility(View.GONE);//加载完网页进度条消失
                } else {
                    mProgressBar.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    mProgressBar.setProgress(newProgress);//设置进度值
                }
            }
            super.onProgressChanged(view, newProgress);
        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            if (!TextUtils.isEmpty(title) && TextUtils.isEmpty(mTitle)) {
                setBarTitle(title);
                hideBarTitle(false);
            }
            super.onReceivedTitle(view, title);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webview.canGoBack()) {
            webview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }*/

    private void injectJS(WebView webview) {
        if (webview != null) {
            webview.loadUrl("javascript:(function() " +
                    "{ " +
                    "document.getElementsByClassName('m-top-bar')[0].style.display='none'; " +
                    "document.getElementsByClassName('m-footer')[0].style.display='none';" +
                    "document.getElementsByClassName('m-page')[0].style.display='none';" +
                    "})()");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


 /*   private void showSheetDailog(ShareModel shareModel) {
        mSheetDialog = new BottomSheetDialog(this);
        ShareDialogView view = new ShareDialogView(this);
        mSheetDialog.setContentView(view);
        view.btCancel.setOnClickListener(v -> mSheetDialog.dismiss());
        view.setOnItemClickListener(position -> {
            shareItem(position, shareModel);
            mSheetDialog.dismiss();
        });
        mSheetDialog.show();
    }

    private void shareItem(int position, ShareModel shareModel) {
        String plat = ShareDialogView.platForm[position];
        Platform platform = ShareSDK.getPlatform(plat);
        Platform.ShareParams sp = new Platform.ShareParams();
        //SHARE_TEXT = 1;SHARE_pathIMAGE = 2;SHARE_urIMAGE = 3;SHARE_WEBPAGE = 4
        if (!TextUtils.isEmpty(shareModel.getShareType())) {
            switch (shareModel.getShareType()) {
                case "1":
                    if (TextUtils.isEmpty(shareModel.getText())) {
                        return;
                    }
                    sp.setText(shareModel.getText());
                    sp.setShareType(Platform.SHARE_TEXT);
                    break;
                case "2":
                    if (TextUtils.isEmpty(shareModel.getImgPath())) {
                        return;
                    }
                    if (position == 1 || position == 0) {
                        Bitmap bitmap = BitmapFactory.decodeFile(shareModel.getImgPath());
                        sp.setImageData(bitmap);
                    } else {
                        sp.setImagePath(shareModel.getImgPath());
                    }
                    sp.setShareType(Platform.SHARE_IMAGE);
                    break;
                case "3":
                    shareImgUrl(position, shareModel, sp);
                    break;
                case "4":
                    shareWebUrl(position, shareModel, sp);
                    break;
                default:
                    shareImgUrl(position, shareModel, sp);
                    break;
            }
        } else {
            shareImgUrl(position, shareModel, sp);
        }
        platform.share(sp);
        platform.setPlatformActionListener(new SharePlatformListener(this));
    }

    private void shareWebUrl(int position, ShareModel shareModel, Platform.ShareParams sp) {
        if (TextUtils.isEmpty(shareModel.getWebUrl())) {
            return;
        }
        if (position == 0) {
            sp.setTitle(shareModel.getText());
        } else {
            sp.setTitle(shareModel.getTitle());
        }
        sp.setText(shareModel.getText());
        sp.setTitleUrl(shareModel.getWebUrl());
        sp.setUrl(shareModel.getWebUrl());
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.share_logo);
        String path = getExternalFilesDir(Environment.DIRECTORY_PICTURES).getPath() + "/" + "share_logo" + ".png";
        BitmapUtils.compressBitmap(bitmap, path);
        if (position == 1 || position == 0) {
            sp.setImageData(bitmap);
        } else {
            sp.setImagePath(path);
        }
        sp.setShareType(Platform.SHARE_WEBPAGE);
    }

    private void shareImgUrl(int position, ShareModel shareModel, Platform.ShareParams sp) {
        if (TextUtils.isEmpty(shareModel.getImgUrl())) {
            return;
        }
        if (position == 1) {
            if (mBitmap == null) {
                ToastUtils.showToast(this, "正在获取图片,请稍候重试");
            } else {
                sp.setImageData(mBitmap);
            }
        } else {
            sp.setImageUrl(shareModel.getImgUrl());
        }
        sp.setShareType(Platform.SHARE_IMAGE);
    }*/

}

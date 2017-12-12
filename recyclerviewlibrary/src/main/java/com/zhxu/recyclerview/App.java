package com.zhxu.recyclerview;

import android.app.Application;
import android.os.Handler;

/**
 * <p>Description:
 *
 * @author xzhang
 */

public class App extends Application {

    private static App context ;



    public static App getContext(){
        return context;
    }
    private static int mMainThreadId;
    private static Handler mHandler;

//    private ApplicationComponent mApplicationComponent ;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this ;
        mHandler=new Handler();
        initApplicationComponent();

//        RxRetrofitApp.init(this, BuildConfig.DEBUG);

    }

    private void initApplicationComponent(){
//        mApplicationComponent = DaggerApplicationComponent.builder()
//                .applicationModule(new ApplicationModule(this)).build();

    }
//    public ApplicationComponent getApplicationComponent(){
//        return mApplicationComponent ;
//    }

    /**
     * 返回主线程的pid
     * @return
     */
    public static int getMainThreadId() {
        return mMainThreadId;
    }
    /**
     * 返回Handler
     * @return
     */
    public static Handler getHandler() {
        return mHandler;
    }
}

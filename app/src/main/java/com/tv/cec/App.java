package com.tv.cec;

import android.app.Application;
import android.os.Handler;

/**
 * Created by Administrator on 2017/12/6.
 */

public class App extends Application {
    private static App context ;
    private static Handler mHandler;
    private static int mMainThreadId;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this ;
        mHandler=new Handler();
    }

    public static App getContext(){
        return context;
    }
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

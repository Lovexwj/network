package com.example.httplibrary.AppBase;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.httplibrary.interceptor.HttpConfig;

/**
 * Created by Chaly on 2017/2/7.get
 */
public class App extends BaseApplication {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化公共参数
        HttpConfig.init(this);

    }

    public static App get() {
        return (App) BaseApplication.get();
    }


}

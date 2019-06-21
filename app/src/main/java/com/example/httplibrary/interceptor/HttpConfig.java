package com.example.httplibrary.interceptor;

import android.content.Context;

import com.example.httplibrary.ApiService;
import com.example.httplibrary.bean.BeanUser;
import com.example.httplibrary.uilts.MyApk;
import com.example.httplibrary.uilts.MyDevice;
import com.example.httplibrary.uilts.MyHttp;
import com.example.httplibrary.uilts.MyLog;

import okhttp3.logging.HttpLoggingInterceptor;

import static com.example.httplibrary.uilts.MyHttp.addHeader;

/**
 * 添加公共参数
 */

public class HttpConfig {
    public static void init(Context con) {
        MyHttp.init(con);
        MyHttp.setBaseUrl(ApiService.HOST).setDebug(HttpLoggingInterceptor.Level.NONE);
        addHeader("User-Agent", "ZTCSH/Android/" + MyApk.getVersionName(con));
        addHeader("regChannel", "3");
        addHeader("devMac", MyDevice.getMac(con));
        addHeader("devOs", "Android");
        addHeader("appVer", MyApk.getVersionName(con));
        addHeader("devType", android.os.Build.MODEL);
        addHeader("accessToken", BeanUser.get(con).getAccessToken());
        MyLog.i("HttpConfig", "accessToken:" + BeanUser.get(con).getAccessToken());
        addHeader("devIp", MyDevice.getIp(con));
        addHeader("trainNo", "" + BeanUser.get(con).getTrainNo());
    }
}

package com.example.httplibrary.uilts;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * 获取当前app的信息
 */
public class MyApk {


    /**
     * 获取版本名称
     *
     * @param con
     * @return
     */
    public static String getVersionName(Context con) {
        String versionName = "Unknown";
        if(con==null)
            return versionName;
        try {
            PackageInfo manager = con.getPackageManager().getPackageInfo(con.getPackageName(), 0);
            versionName = manager.versionName;
        } catch (PackageManager.NameNotFoundException e) {
        }
        MyLog.i("MyApk", "获取App版本名称：" + versionName);
        return versionName;
    }

    /**
     * 获取版本号
     *
     * @param con
     * @return
     */
    public static int getVersionCode(Context con) {
        int versionCode=0;
        try {
            PackageInfo manager = con.getPackageManager().getPackageInfo(con.getPackageName(), 0);
            versionCode=manager.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
        }
        MyLog.i("MyApk", "获取App版本号：" + versionCode);
        return  versionCode;
    }
}

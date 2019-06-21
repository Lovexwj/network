package com.example.httplibrary.uilts;

import android.util.Log;

/**
 * 自定义Log
 */

public class MyLog {

    public static boolean log = true;//是否打印log
    public static void i(Object o, String message) {
        i(o.getClass().getSimpleName(), message);
    }


    public static void i(Object o, Object... message) {
        String log = "";
        for (int i = 0; i < message.length; i++) {
            log += "  " + message[i].toString();
        }
        i(o.getClass().getSimpleName(), log);
    }

    public static void e(Object o, String message) {
        e(o.getClass().getSimpleName(), message);
    }

    public static void d(Object o, String message) {
        d(o.getClass().getSimpleName(), message);
    }


    public static void i(String tag, String message) {
        if (log) {
            Log.i(tag, message);
            Log.i("MyLog",  message);
        }
    }

    public static void e(String tag, String message) {
        if (log) {
            Log.e(tag, message);
            Log.e("MyLog", message);
        }
    }

    public static void d(String tag, String message) {
        if (log) {
            Log.d(tag, message);
            Log.d("MyLog", message);
        }
    }
}

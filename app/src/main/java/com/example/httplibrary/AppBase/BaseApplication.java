package com.example.httplibrary.AppBase;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;

import android.text.TextUtils;
import android.util.Log;



import java.lang.ref.SoftReference;
import java.util.ArrayList;

public class BaseApplication extends Application {

    private SoftReference<Activity> activity;
    private ArrayList<String> acts = new ArrayList<>();

    private int refCount = 0;
    private boolean isHome;


    public boolean isHome() {
        return isHome;
    }

    private Application.ActivityLifecycleCallbacks lifecycleCallback = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            if (!"com.tbruyelle.rxpermissions.ShadowActivity".equals(activity.getClass().getName())) {
                BaseApplication.this.activity = new SoftReference(activity);

            }
            acts.add(activity.getClass().getSimpleName());

            String str = "";
            for (int i = 0; i < acts.size(); i++) {
                str += acts.get(i) + " > ";
            }
            activity.getFragmentManager().addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
                @Override
                public void onBackStackChanged() {
                    Log.i("ActivityManager2", "onBackStackChanged........");
                }
            });


            Log.i("ActivityManager2", str);
        }

        @Override
        public void onActivityStarted(Activity activity) {
            if (!"com.tbruyelle.rxpermissions.ShadowActivity".equals(activity.getClass().getName())) {
                BaseApplication.this.activity = new SoftReference(activity);
            }

            refCount++;
            if (1 == refCount) {//只有当app返回前台时。但进入小说等新建的activity不上传log
                Log.e("gengjunying", "****app在前台了***");
                isHome = false;
            }
        }

        @Override
        public void onActivityResumed(Activity activity) {
            if (!"com.tbruyelle.rxpermissions.ShadowActivity".equals(activity.getClass().getName())) {
                BaseApplication.this.activity = new SoftReference(activity);
            }


        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
            refCount--;
            if (0 == refCount) {
                Log.e("gengjunying", "***app在后台了***");
                isHome = true;
            }


        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            String classNmae = activity.getClass().getSimpleName();
            ArrayList<String> as = new ArrayList<>();
            for (int i = 0; i < acts.size(); i++) {
                if (classNmae.equals(acts.get(i)) == false) {
                    as.add(acts.get(i));
                }
            }
            acts.clear();
            acts.addAll(as);
        }
    };

    protected static class SingletonHolder {
        public static BaseApplication INSTANCE;
    }

    public BaseApplication() {
        registerActivityLifecycleCallbacks(lifecycleCallback);

        SingletonHolder.INSTANCE = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //程序一启动，就将未捕获异常初始化
        //CrashUtil.getInstance().init();
        SingletonHolder.INSTANCE = this;

    }


    public void onLowMemory() {
        System.gc();
        System.runFinalization();
        System.gc();
        super.onLowMemory();
    }

    public Activity getCurrentActivity() {
        if (activity != null)
            return activity.get();
        return null;
    }

    public static BaseApplication get() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取当前进程名
     *
     * @param context
     */
    public static final String getProcessName(Context context) {
        String processName = null;
        // ActivityManager
        ActivityManager am = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE));
        while (true) {
            for (ActivityManager.RunningAppProcessInfo info : am.getRunningAppProcesses()) {
                if (info.pid == android.os.Process.myPid()) {
                    processName = info.processName;
                    break;
                }
            }
            // go home
            if (!TextUtils.isEmpty(processName)) {
                return processName;
            }
            // take a rest and again
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static boolean inMainProcess(Context context) {
        String packageName = context.getPackageName();
        String processName = getProcessName(context);
        return packageName.equals(processName);
    }
//
//    //防止安卓方法数超过65535的导致  Class Not Fount In DexPathList
//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }

}
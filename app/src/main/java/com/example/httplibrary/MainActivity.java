package com.example.httplibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.httplibrary.bean.BannerBean;
import com.example.httplibrary.uilts.MyApk;
import com.example.httplibrary.uilts.MyHttp;
import com.example.httplibrary.uilts.MyLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SetBanner();
    }

    //轮播
    public void SetBanner() {
        MyLog.e(this, "SetBanner: " + "sdsdsdsds");
        String versionName = MyApk.getVersionName(this);
        Map<String, String> map = new HashMap<>();
        map.put("appVer", versionName);
        map.put("devOs", "android");
        map.put("bannerId", "1");
        map.put("advPositionId", "0033");
        MyHttp.with(ApiService.class)
                .PoastBannerBean(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onError(Throwable e) {
                        MyLog.e(this, "SetBanner: " + e.toString());
                    }

                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        List<BannerBean.DataBean.BannersBean> imgesUrl = new ArrayList<>();
                        List<BannerBean.DataBean.BannersBean> banners = bannerBean.getData().getBanners();
                        if (banners.size() > 0) {
                            for (int j = 0; j < banners.size(); j++) {
                                MyLog.e(this, "onNext: " + banners.get(j).getBannerImgUrl());
                                imgesUrl.add(banners.get(j));
                            }
                        } else {
                        }
                    }
                });
    }
}

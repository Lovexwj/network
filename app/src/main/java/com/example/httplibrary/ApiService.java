package com.example.httplibrary;


import com.example.httplibrary.bean.BannerBean;
import com.example.httplibrary.uilts.MyHost;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * 网络请求调用类
 */
public interface ApiService {
    String HOST = MyHost.HOST;
    //Banner查询
    @GET(HOST + "appFrontEnd/v2/appCtrl/banner")
    Observable<BannerBean> PoastBannerBean(@QueryMap Map<String, String> paramer);

}

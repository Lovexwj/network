package com.example.httplibrary.interceptor;


import com.example.httplibrary.uilts.FinalKit;
import com.example.httplibrary.uilts.MyHttp;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Chaly on 16/9/29.
 */

public class HttpCacheInterceptor implements Interceptor {

    //set cahe times is 3 days
    private static final int maxStale = 60 * 60 * 24 * 3;
    // read from cache for 60 s
    private static final int maxOnlineStale = 60;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if (FinalKit.isNetworkAvailable(MyHttp.getContext())) {
            Response response = chain.proceed(request);
            return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, " + String.format("max-stale=%d", maxStale))
                .build();
        } else {
            request = request.newBuilder()
                .cacheControl(CacheControl.FORCE_CACHE)
                .url(chain.request().url())
                .build();
            Response response = chain.proceed(request);
            return response.newBuilder()
                .removeHeader("Pragma")
                .removeHeader("Cache-Control")
                .header("Cache-Control", "public, only-if-cached, " + String.format("max-stale=%d", maxOnlineStale))
                .build();
        }
    }
}
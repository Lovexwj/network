package com.example.httplibrary.interceptor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class DeviceInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        String url = request.url().url().toString();
        if (url.contains("getNodeList") || url.contains("getContentList") || url.contains("getBannerRecommendInfo")) {
            url = url + "&devOs=android&devType=" + android.os.Build.MODEL;
        }
        Response response = chain.proceed(request.newBuilder().url(url).build());
        return response;
    }
}

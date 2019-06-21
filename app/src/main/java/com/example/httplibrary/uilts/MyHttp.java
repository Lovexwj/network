package com.example.httplibrary.uilts;

import android.content.Context;

import com.example.httplibrary.ResultObject;
import com.example.httplibrary.gsonuiles.MyGson;
import com.example.httplibrary.interceptor.DeviceInterceptor;
import com.example.httplibrary.interceptor.HttpBodyInterceptor;
import com.example.httplibrary.interceptor.HttpCacheInterceptor;
import com.example.httplibrary.interceptor.HttpHeaderInterceptor;
import com.example.httplibrary.interceptor.HttpResponseInterceptor;
import com.example.httplibrary.interceptor.NetInterceptor;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.File;
import java.net.Proxy;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cache;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by Chaly on 16/9/24.
 */
public class MyHttp {

    public static final int DEFAULT_TIMEOUT = 10;
    //服务器地址
    private static String BASE_URL = "";
    private static Retrofit retrofit;
    private static HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor();
    public static HttpHeaderInterceptor headerInterceptor = new HttpHeaderInterceptor();
    private static HttpCacheInterceptor cacheInterceptor = new HttpCacheInterceptor();
    private static DeviceInterceptor deviceInterceptor = new DeviceInterceptor();
    //20180105
    private static HttpBodyInterceptor bodyInterceptor = new HttpBodyInterceptor();
    private static HttpResponseInterceptor responseInterceptor = new HttpResponseInterceptor();
    private static Context context;

    public static void init(Context context) {
        MyHttp.context = context;
    }

    public static Context getContext() {
        return MyHttp.context;
    }



    public interface OnResult<T> {
        void onSuccess(boolean success, ResultObject<T, Object> result);

        void onError(Throwable e);
    }

    public static class Singleton {
        public static MyHttp INSTANCE = new MyHttp();
    }


    private Retrofit getRetrofit() {
        if (retrofit == null) {
            Cache cache = null;
            File cacheDirectory = null;
            if (cacheDirectory == null)
                cacheDirectory = new File(MyHttp.context.getCacheDir(), "http_cache");
            try {
                if (cache == null) cache = new Cache(cacheDirectory, 10 * 1024 * 1024);
            } catch (Exception e) {
                MyLog.e("OKHttp", "Could not create http cache"+ e);
            }
            ClearableCookieJar cookieJar = new PersistentCookieJar(
                    new SetCookieCache(), new SharedPrefsCookiePersistor(MyHttp.context)
            );
            OkHttpClient.Builder builder = new OkHttpClient.Builder()
                    .addNetworkInterceptor(logInterceptor)
                    .addInterceptor(headerInterceptor)
                    .addInterceptor(cacheInterceptor)
                    .addInterceptor(new NetInterceptor())
                    .addInterceptor(deviceInterceptor)
                    .addInterceptor(bodyInterceptor)
                    .addInterceptor(responseInterceptor)
                    .connectionPool(new ConnectionPool(20, DEFAULT_TIMEOUT, TimeUnit.SECONDS))
                    .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(false)
                    .proxy(Proxy.NO_PROXY)
                    .cookieJar(cookieJar).cache(cache);

            try {
                // Create a trust manager that does not validate certificate chains
                final TrustManager[] trustAllCerts = new TrustManager[]{
                        new X509TrustManager() {
                            @Override
                            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                            }

                            @Override
                            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                                return new java.security.cert.X509Certificate[]{};
                            }
                        }
                };
                // Install the all-trusting trust manager
                final SSLContext sslContext = SSLContext.getInstance("SSL");
                sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
                // Create an ssl socket factory with our all-trusting manager
                final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
                builder.sslSocketFactory(sslSocketFactory);
                builder.hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
            retrofit = new Retrofit.Builder()
                    .client(builder.build())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(MyGson.get()))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static MyHttp addHeader(String key, String value) {
        Singleton.INSTANCE.headerInterceptor.getHeaders().put(key, value);
        return Singleton.INSTANCE;
    }


    public static <T> T with(Class<T> clazz) {
        return Singleton.INSTANCE.getRetrofit().create(clazz);
    }
    public static MyHttp setBaseUrl(String baseUrl) {
        retrofit = null;
        Singleton.INSTANCE.BASE_URL = baseUrl;
        return Singleton.INSTANCE;
    }
    public static MyHttp setDebug(HttpLoggingInterceptor.Level level) {
        Singleton.INSTANCE.logInterceptor.setLevel(level);
        return Singleton.INSTANCE;
    }

}

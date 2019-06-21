package com.example.httplibrary.interceptor;

import com.example.httplibrary.bean.BeanUser;
import com.example.httplibrary.uilts.MyHttp;
import com.example.httplibrary.uilts.MyLog;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 *打印拦截的数据
 * Created by scx on 2018/01/08.
 */

public class HttpResponseInterceptor implements Interceptor {

    public String getUrl() {
        return url;
    }

    private String url = "";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(chain.request());


        url = request.url() == null ? "" : request.url().toString();
        StringBuffer log = new StringBuffer();
        log.append("\n\nrequest url     =  : " + url);
        log.append("\nrequest body    =  : " + new Gson().toJson(request.body()));
        log.append("\nrequest headers =  : " + new Gson().toJson(request.headers()));
        log.append("\nresponse code   =  : " + response.code());
        MyLog.i("TokenLog", BeanUser.get(MyHttp.getContext()).getAccessToken()+"token");
        MyLog.i("TokenLog", BeanUser.get(MyHttp.getContext()).isLogin()+"islogin");
        if(response.code()==401){
//            BeanUser.get(MyHttpRes.getContext()).setLogin(false);
//            EventBus.getDefault().post(new EventExit());
        }


        if (response.body() != null && response.body().contentType() != null) {

            MediaType mediaType = response.body().contentType();
            String string = response.body().string();
            log.append("\nresponse result =  : " + string);
            MyLog.i("MyHttpRes", log.toString());
            MyLog.i("MyHttpRes",  ".\n\n\n\n.");
            ResponseBody responseBody = ResponseBody.create(mediaType, string);
            return response.newBuilder().body(responseBody).build();
        } else {

            log.append("\nresponse result =  : null");
            MyLog.i("MyHttpRes", log.toString());
            MyLog.i("MyHttpRes",  ".\n\n\n\n.");
            return response;
        }
    }


}

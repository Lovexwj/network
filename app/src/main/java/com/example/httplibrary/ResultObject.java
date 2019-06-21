package com.example.httplibrary;

import com.example.httplibrary.gsonuiles.ResultCode;
import com.example.httplibrary.uilts.MyHttp;
import com.example.httplibrary.uilts.MyLog;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

import retrofit2.HttpException;

public class ResultObject<T, R> {
    //    {
//        "status": 1,        #状态  1成功, 2错误, 3未登录, 需要登录授权,
//        4系统错误(如404, 500错误)
//        "message": null,    #消息内容, 一般在发生错误时的错误提示消息
//        "code": "0",        #消息码
//        "data": null,       #数据体
//    }
    @SerializedName(value = "code", alternate = {"status", "stateId"})
    protected ResultCode code = ResultCode.FAIL;// 0:成功；1:失败
    @SerializedName(value = "msg", alternate = {"message", "errorMsg"})
    protected String msg;// 提示信息，一般是在失败时才有，成功是返回Null
    @SerializedName(value = "data", alternate = {"arrList"})
    protected T data = null;// 请求成功是返回的数据
    protected String timestamp;// 请求相应时间
    @SerializedName(value = "attributes", alternate = {"map"})
    protected Map<String, R> attributes = null;// 附属属性信息

    public static <T> ResultObject success(T... data) {
        ResultObject ro = new ResultObject();
        ro.code = ResultCode.SUCCESS;
        if (data != null && data.length > 0) {
            ro.data = data[0];
        }
        return ro;
    }

    public static ResultObject fail(String msg) {
        ResultObject ro = new ResultObject();
        ro.code = ResultCode.FAIL;
        ro.msg = msg;
        return ro;
    }

    public static ResultObject error(String msg) {
        ResultObject ro = new ResultObject();
        ro.code = ResultCode.EXCEPTION;
        ro.msg = msg;
        return ro;
    }

    /**
     * 添加额外属性信息，以Map的方式呈现
     *
     * @param key   外信息的key
     * @param value 外信息的value
     */
    public void addAttribute(String key, R value) {
        if (attributes == null) {
            attributes = new HashMap<String, R>();
        }
        attributes.put(key, value);
    }

    /**
     * 添加多个额外的属性
     *
     * @param values 以map形式存在的属性值
     */
    public void addAttribute(Map<String, R> values) {
        if (attributes == null) {
            attributes = new HashMap<String, R>();
        }
        attributes.putAll(values);
    }

    /**
     * 重设额外信息集合，并置空attributes
     */
    public void clearAttribute() {
        if (attributes != null) {
            attributes.clear();
            attributes = null;
        }
    }

    public ResultCode getCode() {
        return code;
    }

    public void setCode(ResultCode code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Map<String, R> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, R> attributes) {
        this.attributes = attributes;
    }

    public R getAttributes(String key) {
        return attributes.get(key);
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public static ResultObject getDefaultResponse(Object o) {
        if (o instanceof HttpException && ((HttpException) o).response().code() == 401) {
            ResultObject ro = new ResultObject();
            ro.setCode(ResultCode.UNAUTHORIZED);
            {
                MyHttp.addHeader("x-access-token", "");
//                EventBus.getDefault().post(new EventExit());
//                EventBus.getDefault().post(new UserEvent());
                MyLog.i("App", "token失效2....");
            }
            return ro;
        } else {
            return ResultObject.fail("网络异常");
        }
    }
}

package com.example.httplibrary.gsonuiles;

public enum ResultCode {
    /**
     * 0:访问失败;1:访问成功;2,未登录未授权，3:异常;4:其他情况服务不可用
     */
    SUCCESS(200), FAIL(404),UNAUTHORIZED(401), EXCEPTION(500),  OTHER(503);
    private int value;

    private ResultCode(int value) {    //    必须是private的，否则编译错误
        this.value = value;
    }

    public static ResultCode valueOf(int value) {    //    手写的从int到enum的转换函数
        switch (value) {
            case 200:
                return SUCCESS;
            case 401:
                return UNAUTHORIZED;
            case 500:
                return EXCEPTION;
            case 503:
                return OTHER;
            default:
                return FAIL;
        }
    }

    public int value() {
        return this.value;
    }
}
package com.ideal.cloud.core.utils;

import com.ideal.cloud.core.code.BaseCode;
import com.ideal.cloud.core.code.CodeEnum;
import com.ideal.cloud.core.response.NetResponse;
import com.ideal.cloud.core.response.PriResponse;

public class ResultUtil {
    public static <T> PriResponse<T> constructPri(T t) {
        return new PriResponse<T>(t);
    }

    public static <T> PriResponse<T> constructPri(CodeEnum codeEnum) {
        return new PriResponse<T>(codeEnum);
    }

    public static <T> PriResponse<T> constructPri(String code,String message) {
        return new PriResponse<T>(code,message);
    }

    public static <T> PriResponse<T> constructPri(String message) {
        return new PriResponse<T>(BaseCode.FAIL.getCode(),message);
    }

    public static <T> NetResponse<T> constructNet(T t) {
        return new NetResponse<T>(t);
    }

    public static <T> NetResponse<T> constructNet(CodeEnum codeEnum) {
        return new NetResponse<T>(codeEnum);
    }

    public static <T> NetResponse<T> constructNet(String code,String message) {
        return new NetResponse<T>(code,message);
    }

    public static <T> NetResponse<T> constructNet(String message) {
        return new NetResponse<T>(BaseCode.FAIL.getCode(),message);
    }
}

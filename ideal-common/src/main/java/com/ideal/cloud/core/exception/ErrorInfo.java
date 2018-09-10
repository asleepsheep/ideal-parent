package com.ideal.cloud.core.exception;

import com.ideal.cloud.core.utils.DateHelper;
import lombok.Data;

@Data
public class ErrorInfo<T> {

    private String code;
    private String message;
    private String respTime = DateHelper.getNowString(DateHelper.FormatUnit.yyyyMMddHHmmss, false);
    private T data;

}

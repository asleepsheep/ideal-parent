package com.ideal.cloud.system.exception;

import com.ideal.cloud.core.code.BaseCode;
import com.ideal.cloud.core.exception.BaseException;
import com.ideal.cloud.core.exception.ErrorInfo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public ErrorInfo<Object> baseErrorHandler(BaseException e) throws Exception {
        ErrorInfo<Object> r = new ErrorInfo<>();
        r.setMessage(e.getMessage());
        r.setCode(e.getCode());
        return r;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ErrorInfo<Object> exceptionErrorHandler(BaseException e) throws Exception {
        ErrorInfo<Object> r = new ErrorInfo<>();
        r.setMessage(BaseCode.FAIL.getMessage());
        r.setCode(BaseCode.FAIL.getCode());
        return r;
    }

}

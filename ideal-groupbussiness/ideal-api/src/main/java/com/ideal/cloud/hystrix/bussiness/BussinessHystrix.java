package com.ideal.cloud.hystrix.bussiness;

import com.ideal.cloud.core.response.Response;
import com.ideal.cloud.core.utils.ResultUtil;
import com.ideal.cloud.api.bussiness.BussinessApi;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BussinessHystrix implements BussinessApi {

    @Override
    public String test() {
        return "exception....";
    }

    @Override
    public Response<Map> getTest() {
        return ResultUtil.constructNet("异常情况");
    }
}

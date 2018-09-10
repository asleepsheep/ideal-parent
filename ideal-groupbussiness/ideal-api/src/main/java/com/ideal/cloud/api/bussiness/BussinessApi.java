package com.ideal.cloud.api.bussiness;

import com.ideal.cloud.core.response.Response;
import com.ideal.cloud.hystrix.bussiness.BussinessHystrix;
import com.ideal.cloud.hystrix.bussiness.BussinessHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/***
 * 定义服务的时候，将feign client抽象
 */
@FeignClient(path = "bussiness", value = "${bussiness.name:bussiness-cloud}", fallback = BussinessHystrix.class)
public interface BussinessApi {

    @RequestMapping("/test")
    String test();

    @RequestMapping(value = "/getTest", method = RequestMethod.GET)
    Response<Map> getTest();

}

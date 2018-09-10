package com.ideal.cloud.api.bussiness;

import java.util.List;

import com.ideal.cloud.api.BaseApi;
import com.ideal.cloud.core.response.NetResponse;
import com.ideal.cloud.entity.bussiness.Brand;
import com.ideal.cloud.hystrix.bussiness.BrandHystrix;
import com.ideal.cloud.entity.bussiness.Brand;
import com.ideal.cloud.hystrix.bussiness.BrandHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import com.ideal.cloud.api.BaseApi;


/***
 * 定义服务的时候，将feign client抽象
 */
@FeignClient(path = "", value = "${bussiness.name:bussiness-cloud}", fallback = BrandHystrix.class)
public interface BrandApi extends BaseApi {


    /**
     * 查询所有品牌
     *
     * @return List<Brand>
     */
    @RequestMapping("/brands")
    NetResponse<List<Brand>> queryAllBrands();

    /**
     * 根据id查询单个品牌
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/brands/{id}", method = RequestMethod.GET)
    NetResponse<Brand> queryBrandLike(@PathVariable("id") Long id);

    /**
     * 新增品牌
     *
     * @return
     */
    @RequestMapping(value = "/brands", method = RequestMethod.POST)
    NetResponse<?> addBrand(@RequestBody Brand brand);

    /**
     * 更新品牌
     * @return
     */
    @RequestMapping(value = "/brands", method = RequestMethod.PUT)
    NetResponse<?> updateBrand(Brand brand);

    /**
     * 删除品牌
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/brands/{id}", method = RequestMethod.DELETE)
    NetResponse<?> deleteBrand(@PathVariable("id") Long id);


}


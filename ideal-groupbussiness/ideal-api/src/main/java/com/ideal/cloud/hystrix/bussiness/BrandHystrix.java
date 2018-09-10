package com.ideal.cloud.hystrix.bussiness;

import com.ideal.cloud.api.bussiness.BrandApi;
import com.ideal.cloud.core.response.NetResponse;
import com.ideal.cloud.entity.bussiness.Brand;
import com.ideal.cloud.entity.bussiness.Brand;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BrandHystrix implements BrandApi {

    @Override
    public NetResponse<List<Brand>> queryAllBrands() {
        return null;
    }

    @Override
    public NetResponse<Brand> queryBrandLike(Long id) {
        return null;
    }

    @Override
    public NetResponse<?> addBrand(Brand brand) {
        return null;
    }

    @Override
    public NetResponse<?> updateBrand(Brand brand) {
        return null;
    }

    @Override
    public NetResponse<?> deleteBrand(Long id) {
        return null;
    }
}

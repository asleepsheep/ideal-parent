package com.ideal.cloud.bussiness.service;

import com.ideal.cloud.entity.bussiness.Brand;
import com.ideal.cloud.bussiness.mapper.BrandMapper;
import com.ideal.cloud.bussiness.mapper.BrandMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BrandService {

    @Resource
    private BrandMapper brandMapper;

    public List<Brand> selectAll() {
        return brandMapper.selectAll();
    }

    public Brand selectByPrimaryKey(Long id) {
        return brandMapper.selectByPrimaryKey(id);
    }

    @Transactional
    public void insert(Brand brand) {
        brandMapper.insert(brand);
    }

    public void updateByPrimaryKey(Brand brand) {
        brandMapper.updateByPrimaryKey(brand);
    }

    public void deleteByPrimaryKey(Long id) {
        brandMapper.deleteByPrimaryKey(id);
    }
}

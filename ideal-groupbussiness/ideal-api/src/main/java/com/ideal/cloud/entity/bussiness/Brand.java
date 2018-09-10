package com.ideal.cloud.entity.bussiness;



import lombok.Data;

import java.io.Serializable;

/**
 * Created by jiangfan
 * 2018/8/20 14:23
 */
@Data
public class Brand implements Serializable {

    /**
     * 序列化ID
     */
    private static final Long serialVersionUID = 1L;

    /**
     * 品牌ID
     */
    private Long id;

    /**
     * 品牌名称
     */
    private String name;

    /**
     * 品牌logo
     */
    private String logo;

	/**
     * 品牌首字母
     */
    private String initials;

}

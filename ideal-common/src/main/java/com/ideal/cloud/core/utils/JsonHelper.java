package com.ideal.cloud.core.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;

public class JsonHelper {
    public static String objectToJson(Object object) {
        if(null == object)
            return null;
        return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect);
    }
    public static <T> T jsonToObject(String json,Class<T> clazz) {
        try {
            if(StringHelper.isEmpty(json))
                return null;
            return JSON.parseObject(json, clazz);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String objectToJson(Object object,SerializerFeature... features) {
        return JSON.toJSONString(object, features);
    }
    public static String objectToJsonNoNull(Object object) {
        return JSON.toJSONString(object, SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteNullStringAsEmpty,SerializerFeature.WriteNullListAsEmpty);
    }
    public static <T> T objectToObject(Object object,Class<T> clazz) {
        if(StringHelper.isEmpty(object))
            return null;
        String json = objectToJson(object);
        if(StringHelper.isEmpty(json))
            return null;
        return JSON.parseObject(json, clazz);
    }

    public static <T> List<T> listToList(List<?> list, Class<T> cls){
        if(null == list || list.isEmpty() || list.size() < 1)
            return null;
        return JSON.parseArray(JSON.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect), cls);
    }
    public static <T> List<T> jsonToList(String json,Class<T> cls){
        if(StringHelper.isEmpty(json))
            return null;
        return JSON.parseArray(json, cls);
    }
}

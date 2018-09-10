package com.ideal.cloud.core.utils;

import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;


/**
 * Redis工具类
 */
public class RedisUtils {
	
	/** 默认过期时长，单位：秒 */
	public final static long DEFAULT_EXPIRE = 60 * 60 * 24;
	/** 不设置过期时长 */
	public final static long NOT_EXPIRE = -1;

	/**set value(json格式)，并设置过期时间*/
	public static void set(RedisTemplate<String, String> redisTemplate, String key, Object value, long expire) {
		redisTemplate.opsForValue().set(key, toJson(value));
		if (expire != NOT_EXPIRE) {
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		}
	}

	/**set value(json格式)*/
	public static void set(RedisTemplate<String, String> redisTemplate, String key, Object value) {
		set(redisTemplate,key, value, DEFAULT_EXPIRE);
	}

	/**get value 并设置过期时间，返回对象*/
	public static <T> T get(RedisTemplate<String, String> redisTemplate, String key, Class<T> clazz, long expire) {
		String value = redisTemplate.opsForValue().get(key);
		if (expire != NOT_EXPIRE) {
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		}
		return value == null ? null : fromJson(value, clazz);
	}

	/**get value 返回对象*/
	public static <T> T get(RedisTemplate<String, String> redisTemplate, String key, Class<T> clazz) {
		return get(redisTemplate,key, clazz, NOT_EXPIRE);
	}

	/**get value 并设置过期时间*/
	public static String get(RedisTemplate<String, String> redisTemplate, String key, long expire) {
		String value = redisTemplate.opsForValue().get(key);
		if (expire != NOT_EXPIRE) {
			redisTemplate.expire(key, expire, TimeUnit.SECONDS);
		}
		return value;
	}

	/**get value*/
	public static String get(RedisTemplate<String, String> redisTemplate, String key) {
		return get(redisTemplate,key, NOT_EXPIRE);
	}

	/**删除*/
	public static void delete(RedisTemplate<String, String> redisTemplate, String key) {
		redisTemplate.delete(key);
	}

	/**
	 * Object转成JSON数据
	 */
	private static String toJson(Object object) {
		if (object instanceof Integer || object instanceof Long || object instanceof Float || object instanceof Double
				|| object instanceof Boolean || object instanceof String) {
			return String.valueOf(object);
		}
		return JsonHelper.objectToJson(object);
	}

	/**
	 * JSON数据，转成Object
	 */
	private static <T> T fromJson(String json, Class<T> clazz) {
		return JsonHelper.jsonToObject(json, clazz);
	}
}

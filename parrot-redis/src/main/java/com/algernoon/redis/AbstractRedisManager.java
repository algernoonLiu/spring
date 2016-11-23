/**
 * 
 */
package com.algernoon.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author liuzm
 * @date 2016年11月23日
 * @description TODO
 *
 */
public abstract class AbstractRedisManager<K, V> {
	
	@Autowired
	protected RedisTemplate<K, V> redisTemplate;
	
	public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {  
		this.redisTemplate = redisTemplate;  
    }  
	
	protected RedisSerializer<String> getRedisSerializer() {  
		return redisTemplate.getStringSerializer();  
	}  
}

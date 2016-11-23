/**
 * 
 */
package com.algernoon.dao.impl;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Repository;

import com.algernoon.dao.IUserDao;
import com.algernoon.entity.User;
import com.algernoon.redis.AbstractRedisManager;
import com.algernoon.util.JsonUtil;

/**
 * @author liuzm
 * @date 2016年11月23日
 * @description TODO
 *
 */
@Repository("userDao")
public class UserDaoImpl extends AbstractRedisManager<String, User> implements IUserDao{

	/* (non-Javadoc)
	 * @see com.algernoon.dao.IUserDao#add(com.algernoon.entity.User)
	 */
	public boolean add(final User user) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(user.getId()+"");
				byte[] value = serializer.serialize(JsonUtil.jsonSerialization(user));
				return connection.setNX(key, value);
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.algernoon.dao.IUserDao#delete(long)
	 */
	public boolean delete(final long id) {
		return redisTemplate.execute(new RedisCallback<Boolean>() {

			public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(id+"");
				connection.del(key);
				return true;
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see com.algernoon.dao.IUserDao#get(long)
	 */
	public User get(final long id) {
		return redisTemplate.execute(new RedisCallback<User>() {

			public User doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				byte[] key = serializer.serialize(id+"");
				byte[] value = connection.get(key);
				if (value == null) 
					return null;
				return JsonUtil.jsonDeserialization(serializer.deserialize(value), User.class);
			}
			
		});
	}
	
}

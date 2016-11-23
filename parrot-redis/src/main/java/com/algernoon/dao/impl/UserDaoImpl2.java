/**
 * 
 */
package com.algernoon.dao.impl;

import org.springframework.stereotype.Repository;

import com.algernoon.dao.IUserDao;
import com.algernoon.entity.User;
import com.algernoon.redis.AbstractRedisManager;

/**
 * @author liuzm
 * @date 2016年11月23日
 * @description TODO
 *
 */
@Repository("userDao2")
public class UserDaoImpl2 extends AbstractRedisManager<String, User> implements IUserDao{

	/* (non-Javadoc)
	 * @see com.algernoon.dao.IUserDao#add(com.algernoon.entity.User)
	 */
	public boolean add(User user) {
		redisTemplate.opsForValue().set(user.getId()+"", user);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.algernoon.dao.IUserDao#delete(long)
	 */
	public boolean delete(long id) {
		redisTemplate.opsForValue().set(id+"", null);
		return true;
	}

	/* (non-Javadoc)
	 * @see com.algernoon.dao.IUserDao#get(long)
	 */
	public User get(long id) {
		return redisTemplate.opsForValue().get(id+"");
	}

}

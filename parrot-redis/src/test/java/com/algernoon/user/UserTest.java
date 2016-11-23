/**
 * 
 */
package com.algernoon.user;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.algernoon.dao.IUserDao;
import com.algernoon.entity.User;

/**
 * @author liuzm
 * @date 2016年11月23日
 * @description TODO
 *
 */
@ContextConfiguration("classpath:applicationContext.xml")
public class UserTest extends AbstractJUnit4SpringContextTests{

	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IUserDao userDao2;
	
	@Test
	public void testContext() {
		User user1 = new User(1l, "李阳", "1@gmail.com");
		User user2 = new User(2l, "朴树", "2@gmail.com");
		User user3 = new User(3l, "曙光", "3@gmail.com");
		User user4 = new User(4l, "聊聊", "4@gmail.com");
		
		boolean addResult = userDao.add(user1);
		System.out.println(addResult);
		
		User user = userDao.get(1l);
		System.out.println(user);
		
		boolean addResult2 = userDao2.add(user2);
		System.out.println(addResult2);
		
		user = userDao2.get(2l);
		System.out.println(user);
		
	}
	
}

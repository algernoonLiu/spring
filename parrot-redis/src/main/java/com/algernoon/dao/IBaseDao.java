/**
 * 
 */
package com.algernoon.dao;

import com.algernoon.entity.User;

/**
 * @author liuzm
 * @date 2016年11月23日
 * @description TODO
 *
 */
public interface IBaseDao<T> {

	boolean add(T t);
	
	boolean delete(long id);
	
	User get(long id);
	
}

/**
 * 
 */
package com.algernoon.entity;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * @author liuzm
 * @date 2016年11月23日
 * @description TODO
 *
 */
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private long id;
	
	private String username;

	private String email;

	public User() {}
	
	public User(long id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
}

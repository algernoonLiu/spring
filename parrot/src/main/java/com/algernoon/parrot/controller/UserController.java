/**
 * 
 */
package com.algernoon.parrot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author liuzm
 * @date 2016年11月24日
 * @description TODO
 *
 */
@Controller
@RequestMapping("user")
public class UserController {

	@RequestMapping("getUser")
	public String getUser() {
		return "user";
	}
	
	@RequestMapping("logout")
	public String name() {
		return "login";
	}
}

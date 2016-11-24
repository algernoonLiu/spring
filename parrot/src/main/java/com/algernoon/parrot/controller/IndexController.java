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
public class IndexController {
	
	@RequestMapping("index")
	public String index(){
		return "success";
	}
}

package com.taotao.manage.controller.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 通用跳转控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("page")
public class PageController {
	@RequestMapping("{pageName}")
	public String toPage(@PathVariable("pageName") String pageName){
		return pageName;
	}

}

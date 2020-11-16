/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.api;


import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.vehicle.entity.VNews;
import com.jeeplus.modules.vehicle.service.VNewsService;

/**
 * 新闻资讯APIController
 * @author stephen
 * @version 2019-12-12
 */
@Controller
@RequestMapping(value = "v-api/news")
public class VNewsAPI extends BaseController {

	@Autowired
	private VNewsService vNewsService;
	
	/**
	 * 获取新闻资讯集合
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxJson findList(VNews vNews,HttpServletRequest request) {	
		AjaxJson ajax = new AjaxJson();	
		vNews.setState(true);
		List<VNews> list = vNewsService.findList(vNews);	
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();	
		body.put("list", list);		
		ajax.setBody(body);
		return ajax;
	}
	
	/**
	 * 获取新闻资讯信息
	 */
	@RequestMapping(value = "info")
	@ResponseBody
	public AjaxJson info(VNews vNews,HttpServletRequest request) {	
		AjaxJson ajax = new AjaxJson();	
		vNews = vNewsService.get(vNews);
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();	
		body.put("news", vNews);			
		ajax.setBody(body);
		return ajax;
	}
}
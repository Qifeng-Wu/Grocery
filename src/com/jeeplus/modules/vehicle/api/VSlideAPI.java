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
import com.jeeplus.modules.vehicle.entity.VSlide;
import com.jeeplus.modules.vehicle.service.VSlideService;

/**
 * 幻灯片APIController
 * @author stephen
 * @version 2019-11-23
 */
@Controller
@RequestMapping(value = "v-api/slide")
public class VSlideAPI extends BaseController {
		
	@Autowired
	private VSlideService vSlideService;
	
	/**
	 * 获取显示的幻灯片集合
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxJson findList(VSlide vSlide,HttpServletRequest request) {	
		AjaxJson ajax = new AjaxJson();	
		vSlide.setStatus(true);
		List<VSlide> list = vSlideService.findList(vSlide);
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();	
		body.put("list", list);			
		ajax.setBody(body);
		return ajax;
	}
	
	/**
	 * 获取幻灯片信息
	 */
	@RequestMapping(value = "info")
	@ResponseBody
	public AjaxJson info(VSlide vSlide,HttpServletRequest request) {	
		AjaxJson ajax = new AjaxJson();	
		vSlide = vSlideService.get(vSlide);
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();	
		body.put("slide", vSlide);			
		ajax.setBody(body);
		return ajax;
	}
}
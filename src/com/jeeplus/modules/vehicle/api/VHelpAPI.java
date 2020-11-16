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
import com.jeeplus.modules.vehicle.entity.VHelp;
import com.jeeplus.modules.vehicle.service.VHelpService;

/**
 * 帮助中心APIController
 * @author stephen
 * @version 2019-12-12
 */
@Controller
@RequestMapping(value = "v-api/help")
public class VHelpAPI extends BaseController {

	@Autowired
	private VHelpService vHelpService;
	
	/**
	 * 获取帮助中心集合
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxJson findList(VHelp vHelp,HttpServletRequest request) {	
		AjaxJson ajax = new AjaxJson();	
		vHelp.setState(true);
		List<VHelp> helpList = vHelpService.findList(vHelp);	
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();	
		body.put("helpList", helpList);		
		ajax.setBody(body);
		return ajax;
	}
}
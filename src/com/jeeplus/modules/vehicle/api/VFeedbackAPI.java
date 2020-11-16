/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.api;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.vehicle.entity.VFeedback;
import com.jeeplus.modules.vehicle.service.VFeedbackService;

/**
 * 客户意见反馈APIController
 * @author stephen
 * @version 2019-10-25
 */
@Controller
@RequestMapping(value = "v-api/feedback")
public class VFeedbackAPI extends BaseController {

	@Autowired
	private VFeedbackService vFeedbackService;
	
	/**
	 * 客户意见反馈信息保存
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public synchronized AjaxJson save(VFeedback vFeedback,HttpServletRequest request) {	
		String openId = request.getParameter("openId");
		String type = request.getParameter("type");
		String description = request.getParameter("description");
		String pictures = request.getParameter("pictures");
		AjaxJson ajax = new AjaxJson();	
		if(openId==null||openId.isEmpty()){
			ajax.setSuccess(false);
			ajax.setMsg("提交参数有误");
			ajax.setErrorCode("0");
			return ajax;
		}
		
		vFeedback.setOpenId(openId);
		vFeedback.setType(type);
		vFeedback.setDescription(description);
		vFeedback.setPictures(pictures);
		vFeedback.setCreateTime(new Date());

		vFeedbackService.save(vFeedback);
		return ajax;
	}
	
	/**
	 * 客户获取自己提交的反馈集合
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public synchronized AjaxJson findList(VFeedback vFeedback,HttpServletRequest request) {	
		String openId = request.getParameter("openId");
		AjaxJson ajax = new AjaxJson();	
		if(openId==null||openId.isEmpty()){
			ajax.setSuccess(false);
			ajax.setMsg("提交参数有误");
			ajax.setErrorCode("0");
			return ajax;
		}	
		vFeedback.setOpenId(openId);
		List<VFeedback> list = vFeedbackService.findList(vFeedback);
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();	
		body.put("list", list);		
		ajax.setBody(body);
		return ajax;
	}
}
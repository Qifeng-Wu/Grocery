/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.web;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jeeplus.common.config.Global;
import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.vehicle.entity.VFeedback;
import com.jeeplus.modules.vehicle.service.VFeedbackService;
import com.jeeplus.common.utils.StringUtils;

/**
 * 客户问题反馈Controller
 * @author stephen
 * @version 2019-10-25
 */
@Controller
@RequestMapping(value = "${adminPath}/vehicle/feedback")
public class VFeedbackController extends BaseController {

	@Autowired
	private VFeedbackService vFeedbackService;
	
	@ModelAttribute
	public VFeedback get(@RequestParam(required=false) String id) {
		VFeedback entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = vFeedbackService.get(id);
		}
		if (entity == null){
			entity = new VFeedback();
		}
		return entity;
	}
	
	/**
	 * 意见反馈列表页面
	 */
	//@RequiresPermissions("qis:VFeedback:list")
	@RequestMapping(value = {"list", ""})
	public String list(@ModelAttribute("vFeedback")VFeedback vFeedback, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VFeedback> page = vFeedbackService.findPage(new Page<VFeedback>(request, response), vFeedback); 
		model.addAttribute("page", page);
		return "modules/vehicle/feedbackList";
	}

	/**
	 * 查看，增加，编辑意见反馈表单页面
	 */
	//@RequiresPermissions(value={"qis:VFeedback:view","qis:VFeedback:add","qis:VFeedback:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(VFeedback vFeedback, Model model) {
		model.addAttribute("vFeedback", vFeedback);
		return "modules/vehicle/feedbackForm";
	}

	/**
	 * 保存意见反馈
	 */
	//@RequiresPermissions(value={"qis:VFeedback:add","qis:VFeedback:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(VFeedback vFeedback, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, vFeedback)){
			return form(vFeedback, model);
		}
		
		vFeedbackService.save(vFeedback);
		addMessage(redirectAttributes, "保存意见反馈成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/feedback/?repage";
	}
	
	/**
	 * 删除意见反馈
	 */
	//@RequiresPermissions("qis:VFeedback:del")
	@RequestMapping(value = "delete")
	public String delete(VFeedback vFeedback, RedirectAttributes redirectAttributes) {
		vFeedbackService.delete(vFeedback);
		addMessage(redirectAttributes, "删除意见反馈成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/feedback/?repage";
	}
	
	/**
	 * 批量删除意见反馈
	 */
	//@RequiresPermissions("qis:VFeedback:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids,VFeedback vFeedback,RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			vFeedback = vFeedbackService.get(id);
			vFeedbackService.delete(vFeedback);
		}
		addMessage(redirectAttributes, "删除意见反馈成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/feedback/?repage";
	}

}
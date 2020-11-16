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
import com.jeeplus.modules.vehicle.entity.VHelp;
import com.jeeplus.modules.vehicle.service.VHelpService;
import com.jeeplus.common.utils.StringUtils;

/**
 * 帮助中心Controller
 * @author stephen
 * @version 2019-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/vehicle/help")
public class VHelpController extends BaseController {

	@Autowired
	private VHelpService vHelpService;
	
	@ModelAttribute
	public VHelp get(@RequestParam(required=false) String id) {
		VHelp entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = vHelpService.get(id);
		}
		if (entity == null){
			entity = new VHelp();
		}
		return entity;
	}
	
	/**
	 * 帮助中心列表页面
	 */
	@RequestMapping(value = {"list", ""})
	public String list(@ModelAttribute("vHelp")VHelp vHelp, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VHelp> page = vHelpService.findPage(new Page<VHelp>(request, response), vHelp); 
		model.addAttribute("page", page);
		return "modules/vehicle/helpList";
	}

	/**
	 * 查看，增加，编辑帮助中心表单页面
	 */
	@RequestMapping(value = "form")
	public String form(VHelp vHelp, Model model) {
		model.addAttribute("vHelp", vHelp);
		return "modules/vehicle/helpForm";
	}

	/**
	 * 保存帮助中心
	 */
	@RequestMapping(value = "save")
	public String save(VHelp vHelp, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, vHelp)){
			return form(vHelp, model);
		}
		vHelp.setTitle(request.getParameter("title"));
		vHelp.setContent(request.getParameter("content"));
		vHelpService.save(vHelp);
		addMessage(redirectAttributes, "保存帮助中心成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/help/?repage";
	}
	
	/**
	 * 删除帮助中心
	 */
	@RequestMapping(value = "delete")
	public String delete(VHelp vHelp, RedirectAttributes redirectAttributes) {
		vHelpService.delete(vHelp);
		addMessage(redirectAttributes, "删除帮助中心成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/help/?repage";
	}
	
	/**
	 * 批量删除帮助中心
	 */
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids,VHelp vHelp,RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			vHelpService.delete(vHelpService.get(id));
		}
		addMessage(redirectAttributes, "删除帮助中心成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/help/?repage";
	}

}
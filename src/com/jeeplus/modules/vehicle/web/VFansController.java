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
import com.jeeplus.modules.vehicle.entity.VFans;
import com.jeeplus.modules.vehicle.service.VFansService;
import com.jeeplus.common.utils.StringUtils;

/**
 * 粉丝用户Controller
 * @author stephen
 * @version 2019-11-25
 */
@Controller
@RequestMapping(value = "${adminPath}/vehicle/fans")
public class VFansController extends BaseController {

	@Autowired
	private VFansService vFansService;
	
	@ModelAttribute
	public VFans get(@RequestParam(required=false) String id) {
		VFans entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = vFansService.get(id);
		}
		if (entity == null){
			entity = new VFans();
		}
		return entity;
	}
	
	/**
	 * 列表页面
	 */
	@RequestMapping(value = {"list", ""})
	public String list(@ModelAttribute("vFans")VFans vFans, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VFans> page = vFansService.findPage(new Page<VFans>(request, response), vFans); 
		model.addAttribute("page", page);
		return "modules/vehicle/fansList";
	}
	
	/**
	 * 查看，增加，编辑表单页面
	 */
	@RequestMapping(value = "form")
	public String form(VFans VFans, Model model) {
		model.addAttribute("VFans", VFans);
		return "modules/vehicle/fansForm";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "save")
	public String save(VFans vFans, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, vFans)){
			return form(vFans, model);
		}
		vFansService.customSave(vFans);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/fans/?repage";
	}

	
	/**
	 * 删除
	 */
	@RequestMapping(value = "delete")
	public String delete(VFans vFans, RedirectAttributes redirectAttributes) {
		vFansService.delete(vFans);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/fans/?repage";
	}
	
}
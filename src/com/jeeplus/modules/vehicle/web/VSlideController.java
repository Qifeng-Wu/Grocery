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
import com.jeeplus.modules.vehicle.entity.VSlide;
import com.jeeplus.modules.vehicle.service.VSlideService;
import com.jeeplus.common.utils.StringUtils;

/**
 * 幻灯片Controller
 * @author stephen
 * @version 2019-10-25
 */
@Controller
@RequestMapping(value = "${adminPath}/vehicle/slide")
public class VSlideController extends BaseController {

	@Autowired
	private VSlideService vSlideService;
	
	@ModelAttribute
	public VSlide get(@RequestParam(required=false) String id) {
		VSlide entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = vSlideService.get(id);
		}
		if (entity == null){
			entity = new VSlide();
		}
		return entity;
	}
	
	/**
	 * 列表页面
	 */
	@RequestMapping(value = {"list", ""})
	public String list(@ModelAttribute("vSlide")VSlide vSlide, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VSlide> page = vSlideService.findPage(new Page<VSlide>(request, response), vSlide); 
		model.addAttribute("page", page);
		return "modules/vehicle/slideList";
	}

	/**
	 * 查看，增加，编辑表单页面
	 */
	@RequestMapping(value = "form")
	public String form(VSlide vSlide, Model model) {
		model.addAttribute("vSlide", vSlide);
		return "modules/vehicle/slideForm";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "save")
	public String save(VSlide vSlide, HttpServletRequest request, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, vSlide)){
			return form(vSlide, model);
		}
		vSlide.setTitle(request.getParameter("title"));
		vSlide.setDescription(request.getParameter("description"));
		vSlideService.customSave(vSlide);
		addMessage(redirectAttributes, "保存幻灯片成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/slide/?repage";
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "delete")
	public String delete(VSlide vSlide, RedirectAttributes redirectAttributes) {
		vSlideService.delete(vSlide);
		addMessage(redirectAttributes, "删除幻灯片成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/slide/?repage";
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			vSlideService.delete(vSlideService.get(id));
		}
		addMessage(redirectAttributes, "删除幻灯片成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/slide/?repage";
	}
}
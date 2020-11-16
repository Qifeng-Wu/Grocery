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
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.vehicle.entity.VFuel;
import com.jeeplus.modules.vehicle.service.VFuelService;

/**
 * 加油记录Controller
 * @author stephen
 * @version 2019-11-24
 */
@Controller
@RequestMapping(value = "${adminPath}/vehicle/fuel")
public class VFuelController extends BaseController {

	@Autowired
	private VFuelService vFuelService;
	
	@ModelAttribute
	public VFuel get(@RequestParam(required=false) String id) {
		VFuel entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = vFuelService.get(id);
		}
		if (entity == null){
			entity = new VFuel();
		}
		return entity;
	}
	
	/**
	 * 加油记录列表页面
	 */
	//@RequiresPermissions("vehicle:VFuel:list")
	@RequestMapping(value = {"list", ""})
	public String list(@ModelAttribute("vFuel")VFuel vFuel, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VFuel> page = vFuelService.findPage(new Page<VFuel>(request, response), vFuel); 
		model.addAttribute("page", page);
		return "modules/vehicle/fuelList";
	}

	/**
	 * 查看，增加，编辑加油记录表单页面
	 */
	//@RequiresPermissions(value={"vehicle:VFuel:view","vehicle:VFuel:add","vehicle:VFuel:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(VFuel vFuel, Model model) {
		model.addAttribute("vFuel", vFuel);
		return "modules/vehicle/fuelForm";
	}

	/**
	 * 保存加油记录
	 */
	//@RequiresPermissions(value={"vehicle:VFuel:add","vehicle:VFuel:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(VFuel vFuel, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, vFuel)){
			return form(vFuel, model);
		}
		vFuelService.save(vFuel);
		addMessage(redirectAttributes, "保存加油记录成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/fuel/?repage";
	}
	
	/**
	 * 删除加油记录
	 */
	//@RequiresPermissions("vehicle:VFuel:del")
	@RequestMapping(value = "delete")
	public String delete(VFuel vFuel, RedirectAttributes redirectAttributes) {
		vFuelService.delete(vFuel);
		addMessage(redirectAttributes, "删除加油记录成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/fuel/?repage";
	}
	
	/**
	 * 批量删除加油记录
	 */
	//@RequiresPermissions("vehicle:VFuel:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			vFuelService.delete(vFuelService.get(id));
		}
		addMessage(redirectAttributes, "删除加油记录成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/fuel/?repage";
	}
}
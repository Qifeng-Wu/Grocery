/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.animal.web;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.common.utils.StringUtils;
import com.jeeplus.modules.animal.entity.DWAnimal;
import com.jeeplus.modules.animal.service.DWAnimalService;

/**
 * 用户动物记录Controller
 * @author stephen
 * @version 2020-11-12
 */
@Controller
@RequestMapping(value = "${adminPath}/dw/animal")
public class DWAnimalController extends BaseController {

	@Autowired
	private DWAnimalService dWAnimalService;
	
	@ModelAttribute
	public DWAnimal get(@RequestParam(required=false) String id) {
		DWAnimal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = dWAnimalService.get(id);
		}
		if (entity == null){
			entity = new DWAnimal();
		}
		return entity;
	}
	
	/**
	 * 用户动物记录列表页面
	 */
	@RequestMapping(value = {"list", ""})
	public String list(@ModelAttribute("dwAnimal")DWAnimal dwAnimal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<DWAnimal> page = dWAnimalService.findPage(new Page<DWAnimal>(request, response), dwAnimal); 
		model.addAttribute("page", page);
		return "modules/vehicle/dwAnimalList";
	}
}
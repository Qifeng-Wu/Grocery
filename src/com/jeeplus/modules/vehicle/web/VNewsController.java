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
import com.jeeplus.modules.vehicle.entity.VNews;
import com.jeeplus.modules.vehicle.service.VNewsService;
import com.jeeplus.common.utils.StringUtils;

/**
 * 新闻资讯Controller
 * @author stephen
 * @version 2019-12-12
 */
@Controller
@RequestMapping(value = "${adminPath}/vehicle/news")
public class VNewsController extends BaseController {

	@Autowired
	private VNewsService vNewsService;
	
	@ModelAttribute
	public VNews get(@RequestParam(required=false) String id) {
		VNews entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = vNewsService.get(id);
		}
		if (entity == null){
			entity = new VNews();
		}
		return entity;
	}
	
	/**
	 * 列表页面
	 */
	@RequestMapping(value = {"list", ""})
	public String list(@ModelAttribute("vNews")VNews vNews, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<VNews> page = vNewsService.findPage(new Page<VNews>(request, response), vNews); 
		model.addAttribute("page", page);
		return "modules/vehicle/newsList";
	}

	/**
	 * 查看，增加，编辑表单页面
	 */
	@RequestMapping(value = "form")
	public String form(VNews vNews, Model model) {
		model.addAttribute("vNews", vNews);
		return "modules/vehicle/newsForm";
	}

	/**
	 * 保存
	 */
	@RequestMapping(value = "save")
	public String save(VNews vNews, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, vNews)){
			return form(vNews, model);
		}
		vNews.setTitle(request.getParameter("title"));
		vNews.setSummary(request.getParameter("summary"));
		vNews.setPicture(request.getParameter("picture"));
		vNews.setContent(request.getParameter("content"));
		vNewsService.save(vNews);
		addMessage(redirectAttributes, "保存成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/news/?repage";
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "delete")
	public String delete(VNews vNews, RedirectAttributes redirectAttributes) {
		vNewsService.delete(vNews);
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/news/?repage";
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids,VNews vNews,RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			vNewsService.delete(vNewsService.get(id));
		}
		addMessage(redirectAttributes, "删除成功");
		return "redirect:"+Global.getAdminPath()+"/vehicle/news/?repage";
	}

}
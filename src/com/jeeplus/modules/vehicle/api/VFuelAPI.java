/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.api;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.jeeplus.modules.vehicle.entity.VFuel;
import com.jeeplus.modules.vehicle.service.VFuelService;

/**
 *油耗记录APIController
 * @author stephen
 * @version 2019-11-23
 */
@Controller
@RequestMapping(value = "v-api/fuel")
public class VFuelAPI extends BaseController {
		
	@Autowired
	private VFuelService vFuelService;
	
	/**
	 * 获取该用户下的加油记录列表
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxJson findList(VFuel vFuel,HttpServletRequest request) {	
		String openId = request.getParameter("openId");
		AjaxJson ajax = new AjaxJson();	
		vFuel.setOpenId(openId);
		List<VFuel> list = vFuelService.findList(vFuel);
		if(list!=null && list.size()>0) {
			List<VFuel> newlist = list;
			Integer dmileage ;
			BigDecimal consumption ;
			for(int i=list.size()-1; i>=0; i--) {
				if(i > 0) {
					dmileage = newlist.get(i-1).getCmileage() + newlist.get(i-1).getRest() 
							- (list.get(i).getCmileage() + list.get(i).getRest());
					consumption = list.get(i).getMoney().divide(list.get(i).getPrice(),4,BigDecimal.ROUND_HALF_UP)
							.divide(new BigDecimal(dmileage),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
				}else {
					dmileage = 0;
					consumption = new BigDecimal(0);
				}
				list.get(i).setDmileage(dmileage);
				list.get(i).setConsumption(consumption.setScale(2, BigDecimal.ROUND_HALF_UP));
			}
		}
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();	
		body.put("list", list);			
		ajax.setBody(body);
		return ajax;
	}
	
	/**
	 * 搜索该用户下的加油记录列表
	 * @throws ParseException 
	 */
	@RequestMapping(value = "search")
	@ResponseBody
	public AjaxJson search(VFuel vFuel,HttpServletRequest request) throws ParseException {	
		String openId = request.getParameter("openId");
		String date = request.getParameter("date");
		AjaxJson ajax = new AjaxJson();	
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		vFuel.setTime(sdf.parse(date));
		vFuel.setOpenId(openId);
		List<VFuel> list = vFuelService.findList(vFuel);
		List<VFuel> newlist = list;
		if(list!=null && list.size()>0) {
			Integer dmileage ;
			BigDecimal consumption ;
			for(int i=list.size()-1; i>=0; i--) {
				if(i > 0) {
					dmileage = newlist.get(i-1).getCmileage() + newlist.get(i-1).getRest() 
							- (list.get(i).getCmileage() + list.get(i).getRest());
					consumption = list.get(i).getMoney().divide(list.get(i).getPrice(),4,BigDecimal.ROUND_HALF_UP)
							.divide(new BigDecimal(dmileage),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
				}else {
					dmileage = 0;
					consumption = new BigDecimal(0);
				}
				list.get(i).setDmileage(dmileage);
				list.get(i).setConsumption(consumption.setScale(2, BigDecimal.ROUND_HALF_UP));
			}
		}
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();	
		body.put("list", list);			
		ajax.setBody(body);
		return ajax;
	}
	
	/**
	 * 保存加油记录信息
	 * @throws ParseException 
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public synchronized AjaxJson save(VFuel vFuel,HttpServletRequest request) throws ParseException {	
		if(vFuel.getFuelId().equals(0)) {
			vFuel.setFuelId(null);
		}
		String openId = request.getParameter("openId");
		String price = request.getParameter("price");
		String money = request.getParameter("money");
		String cmileage = request.getParameter("cmileage");
		String rest = request.getParameter("rest");
		String oil = request.getParameter("oil");
		String time = request.getParameter("time");
		String remark = request.getParameter("remark");
		AjaxJson ajax = new AjaxJson();	
		if(openId==null||openId.isEmpty()){
			ajax.setSuccess(false);
			ajax.setMsg("提交参数有误");
			ajax.setErrorCode("0");
			return ajax;
		}
		
		vFuel.setOpenId(openId);
		vFuel.setPrice(new BigDecimal(price));
		vFuel.setMoney(new BigDecimal(money));
		vFuel.setCmileage(Integer.parseInt(cmileage));
		vFuel.setRest(Integer.parseInt(rest));
		vFuel.setOil(oil);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		vFuel.setTime(sdf.parse(time));
		vFuel.setCreatetime(new Date());
		vFuel.setRemark(remark);
		vFuelService.customSave(vFuel);
		return ajax;
	}
	
	/**
	 * 通过主键获取商品信息
	 */
	@RequestMapping(value = "info")
	@ResponseBody
	public AjaxJson goodsInfo(VFuel vFuel,HttpServletRequest request) {
		String fuelId = request.getParameter("fuelId");
		AjaxJson ajax = new AjaxJson();	
		vFuel = vFuelService.get(fuelId);	
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();		
		body.put("vFuel", vFuel);			
		ajax.setBody(body);
		return ajax;
	}
	
	/**
	 * 删除加油记录
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public synchronized AjaxJson delete(VFuel vFuel) {	
		AjaxJson ajax = new AjaxJson();
		vFuelService.delete(vFuel);
		return ajax;
	}
}
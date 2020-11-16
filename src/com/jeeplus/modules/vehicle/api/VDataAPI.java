/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.api;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jeeplus.common.json.AjaxJson;
import com.jeeplus.common.web.BaseController;
import com.jeeplus.modules.vehicle.dao.VDataDao;
import com.jeeplus.modules.vehicle.entity.VFuel;

/**
 * 数据统计APIController
 * @author stephen
 * @version 2019-11-23
 */
@Controller
@RequestMapping(value = "v-api/fuel/data")
public class VDataAPI extends BaseController {
	
	@Autowired
	private VDataDao vDataDao;
	
	/**
	 * 炫车宝小程序首页数据统计
	 */
	@RequestMapping(value = "index")
	@ResponseBody
	public AjaxJson findList(HttpServletRequest request) {	
		String openId = request.getParameter("openId");
		AjaxJson ajax = new AjaxJson();	
		//Integer usedays = vDataDao.fansUseDays(openId);//使用天数
		BigDecimal summoney = new BigDecimal("0.00");//总计油费
		BigDecimal sumquantity = new BigDecimal("0.00");//总加油量
		BigDecimal daymoney = new BigDecimal("0.00");//每日油费
		BigDecimal avgprice = new BigDecimal("0.00");//平均油价
		BigDecimal avgconsumption = new BigDecimal("0.00") ;//平均油耗
		BigDecimal avgfee = new BigDecimal("0.00");//平均油费
		BigDecimal avgmileage = new BigDecimal("0.00");//平均行程
		BigDecimal days;
				
		 List<Map<String, Object>> mapList = vDataDao.fansFuelFee(openId);
		 if(mapList!=null && mapList.size()>0) {
		    Map<String, Object> map = mapList.get(0);
		    if(map!=null) {	   		   
				sumquantity = new BigDecimal(String.valueOf(map.get("sumquantity")));
				summoney = new BigDecimal(String.valueOf(map.get("summoney")));
				days = new BigDecimal(String.valueOf(map.get("days")));			 		
				avgprice = summoney.divide(sumquantity,2,BigDecimal.ROUND_HALF_UP);
				
				List<VFuel> list = vDataDao.findTwoVfuelList(openId);
				System.out.println(list.size()+"======"+list);
				if(list!=null && list.size()>0) {
					if(list!=null && list.size()>1) {
						Integer dmileage = list.get(1).getCmileage() + list.get(1).getRest() 
								- (list.get(0).getCmileage() + list.get(0).getRest());
						BigDecimal fuelFees = summoney.subtract(list.get(1).getMoney()); //除去最后一次的总加油费
						BigDecimal fuelQuantity = sumquantity.subtract((list.get(1).getMoney().divide(list.get(1).getPrice(),2,BigDecimal.ROUND_HALF_UP)));////除去最后一次的总加油量
						avgfee =  fuelFees.divide(new BigDecimal(dmileage),2,BigDecimal.ROUND_HALF_UP);
						avgconsumption = fuelQuantity.divide(new BigDecimal(dmileage),4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
						avgmileage = new BigDecimal(dmileage).divide(days,2,BigDecimal.ROUND_HALF_UP);	
						daymoney = avgfee.multiply(avgmileage);
					}else {
						avgconsumption = new BigDecimal("0.00");//平均油耗
						avgfee = new BigDecimal("0.00");//平均油费
						avgmileage = new BigDecimal("0.00") ;//平均行程
						daymoney = new BigDecimal("0.00");//每日油费
					}			
				}else {
					summoney = new BigDecimal("0.00");//总计油费
					daymoney = new BigDecimal("0.00");//每日油费
					avgconsumption = new BigDecimal("0.00");//平均油耗
					avgfee = new BigDecimal("0.00");//平均油费
					avgmileage = new BigDecimal("0.00") ;//平均行程
				}
			}
		}
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();	
		body.put("summoney", summoney);
		body.put("sumquantity", sumquantity);	
		body.put("daymoney", daymoney);
		body.put("avgprice", avgprice);
		body.put("avgmileage", avgmileage);
		body.put("avgfee", avgfee);
		body.put("avgconsumption", avgconsumption);
		ajax.setBody(body);
		return ajax;
	}
}
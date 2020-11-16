/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.animal.api;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.jeeplus.modules.animal.dao.DWAnimalDao;
import com.jeeplus.modules.animal.entity.DWAnimal;
import com.jeeplus.modules.animal.service.DWAnimalService;

/**
 * 动物记录APIController
 * @author stephen
 * @version 2020-11-09
 */
@Controller
@RequestMapping(value = "dw-api/animal")
public class DWAnimalAPI extends BaseController {
		
	@Autowired
	private DWAnimalService dwAnimalService;
	
	@Autowired
	private DWAnimalDao dwAnimalDao;
	
	/**
	 * 获取该用户下的动物记录
	 */
	@RequestMapping(value = "list")
	@ResponseBody
	public AjaxJson list(DWAnimal dwAnimal, HttpServletRequest request) {	
		String openId = request.getParameter("openId");
		AjaxJson ajax = new AjaxJson();	
		if(openId==null||openId.isEmpty()){
			ajax.setSuccess(false);
			ajax.setMsg("提交参数有误");
			ajax.setErrorCode("0");
			return ajax;
		}
		
		dwAnimal.setOpenId(openId);
		List<DWAnimal> list = dwAnimalService.findList(dwAnimal);
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();	
		body.put("list", list);			
		ajax.setBody(body);
		return ajax;
	}
	
	/**
	 * 保存（包括新增和编辑）动物记录信息
	 * @throws ParseException 
	 */
	@RequestMapping(value = "save")
	@ResponseBody
	public synchronized AjaxJson save(DWAnimal dwAnimal, HttpServletRequest request) throws ParseException {	
		if(dwAnimal.getAnimalId().equals(0)) {
			dwAnimal.setAnimalId(null);
		}
		String openId = request.getParameter("openId");
		String account = request.getParameter("account");
		String name = request.getParameter("name");
		String no = request.getParameter("no");
		String price = request.getParameter("price");
		String profit = request.getParameter("profit");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		String remark = request.getParameter("remark");
		AjaxJson ajax = new AjaxJson();	
		if(openId==null||openId.isEmpty()){
			ajax.setSuccess(false);
			ajax.setMsg("提交参数有误");
			ajax.setErrorCode("0");
			return ajax;
		}
		
		dwAnimal.setOpenId(openId);
		dwAnimal.setAccount(account);
		dwAnimal.setName(name);
		dwAnimal.setNo(no);
		dwAnimal.setPrice(new BigDecimal(price));
		dwAnimal.setProfit(new BigDecimal(profit));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		dwAnimal.setStartTime(sdf.parse(startTime));
		dwAnimal.setEndTime(sdf.parse(endTime));
		dwAnimal.setCreateTime(new Date());
		dwAnimal.setRemark(remark);
		dwAnimalService.customSave(dwAnimal);
		return ajax;
	}
	
	/**
	 * 通过主键获取动物信息
	 */
	@RequestMapping(value = "info")
	@ResponseBody
	public AjaxJson goodsInfo(DWAnimal dwAnimal, HttpServletRequest request) {
		String animalId = request.getParameter("animalId");
		AjaxJson ajax = new AjaxJson();	
		dwAnimal = dwAnimalService.get(animalId);	
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();		
		body.put("dwAnimal", dwAnimal);			
		ajax.setBody(body);
		return ajax;
	}
	
	/**
	 * 删除动物记录
	 */
	@RequestMapping(value = "delete")
	@ResponseBody
	public synchronized AjaxJson delete(DWAnimal dwAnimal) {	
		AjaxJson ajax = new AjaxJson();
		dwAnimalService.delete(dwAnimal);
		return ajax;
	}
	
	/**
	 * 获取该用户数据统计
	 */
	@RequestMapping(value = "data")
	@ResponseBody
	public AjaxJson data(HttpServletRequest request) {	
		String openId = request.getParameter("openId");
		AjaxJson ajax = new AjaxJson();	
		BigDecimal sumPrice = new BigDecimal("0.00");//动物总成本
		BigDecimal sumProfit = new BigDecimal("0.00");//动物总利润
		BigDecimal sumMoney = new BigDecimal("0.00");//动物总本息
		BigDecimal unsoldSumPrice = new BigDecimal("0.00");//未交易的动物总成本
		BigDecimal unsoldSumProfit = new BigDecimal("0.00");//未交易的动物总利润
		BigDecimal unsoldSumMoney = new BigDecimal("0.00");//未交易的动物总本息
		
		List<Map<String, Object>> sumList = dwAnimalDao.findSum(openId);
		List<Map<String, Object>> unsoldSumList = dwAnimalDao.findUnsoldSum(openId);
		
		if(sumList!=null && sumList.size()>0) {
		    Map<String, Object> sumMap = sumList.get(0);
		    if(sumMap!=null) {	   		   
		    	sumPrice = new BigDecimal(String.valueOf(sumMap.get("sumprice")));
		    	sumProfit = new BigDecimal(String.valueOf(sumMap.get("sumprofit")));
				sumMoney = sumPrice.add(sumProfit).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
		}
		
		if(unsoldSumList!=null && unsoldSumList.size()>0) {
		    Map<String, Object> unsoldSumMap = unsoldSumList.get(0);
		    if(unsoldSumMap!=null) {	   		   
		    	unsoldSumPrice = new BigDecimal(String.valueOf(unsoldSumMap.get("sumprice")));
		    	unsoldSumProfit = new BigDecimal(String.valueOf(unsoldSumMap.get("sumprofit")));
		    	unsoldSumMoney = unsoldSumPrice.add(unsoldSumProfit).setScale(2,BigDecimal.ROUND_HALF_UP);
			}
		}
		
		LinkedHashMap<String, Object> body = new LinkedHashMap<String, Object>();	
		body.put("sumPrice", sumPrice);	
		body.put("sumProfit", sumProfit);
		body.put("sumMoney", sumMoney);
		body.put("unsoldSumPrice", unsoldSumPrice);
		body.put("unsoldSumProfit", unsoldSumProfit);
		body.put("unsoldSumMoney", unsoldSumMoney);			
		ajax.setBody(body);
		return ajax;
	}
}
package com.jeeplus.modules.vehicle.dao;

import java.util.List;
import java.util.Map;

import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.vehicle.entity.VFuel;

/**
 * 数据统计DAO接口
 * @author stephen
 * @version 2019-11-25
 */
@MyBatisDao
public interface VDataDao {
	//根据主键获取该用户使用天数 
	Integer fansUseDays(String openId);
	//根据主键获取该用户总计油费、总加油量、两次加油天数差
	List<Map<String, Object>> fansFuelFee(String openId);
	//根据主键获取加油记录第一条数据和最后一条数据
	List<VFuel> findTwoVfuelList(String openId);
	
}
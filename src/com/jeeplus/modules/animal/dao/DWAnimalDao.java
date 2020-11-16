/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.animal.dao;

import java.util.List;
import java.util.Map;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.animal.entity.DWAnimal;

/**
 * 动物记录DAO接口
 * @author stephen
 * @version 2020-11-09
 */
@MyBatisDao
public interface DWAnimalDao extends CrudDao<DWAnimal> {
	//根据OPEMID获取该用户总投入成本、总利润
	List<Map<String, Object>> findSum(String openId);
	//根据OPEMID获取该用户未交易的动物的总投入成本、总利润 
	List<Map<String, Object>> findUnsoldSum(String openId);
}
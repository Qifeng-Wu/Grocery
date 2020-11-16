/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.vehicle.entity.VFuel;

/**
 * 加油记录DAO接口
 * @author stephen
 * @version 2019-11-24
 */
@MyBatisDao
public interface VFuelDao extends CrudDao<VFuel> {
	
}
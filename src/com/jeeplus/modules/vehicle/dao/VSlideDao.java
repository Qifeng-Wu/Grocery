/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.vehicle.entity.VSlide;

/**
 * 幻灯片DAO接口
 * @author stephen
 * @version 2019-11-25
 */
@MyBatisDao
public interface VSlideDao extends CrudDao<VSlide> {
	
}
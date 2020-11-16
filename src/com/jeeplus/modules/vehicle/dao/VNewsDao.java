/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.vehicle.entity.VNews;

/**
 * 新闻资讯DAO接口
 * @author stephen
 * @version 2019-12-12
 */
@MyBatisDao
public interface VNewsDao extends CrudDao<VNews> {
	
}
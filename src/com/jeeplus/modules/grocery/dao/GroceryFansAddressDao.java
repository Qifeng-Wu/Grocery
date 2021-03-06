/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.grocery.dao;

import com.jeeplus.common.persistence.CrudDao;
import com.jeeplus.common.persistence.annotation.MyBatisDao;
import com.jeeplus.modules.grocery.entity.GroceryFansAddress;

/**
 * 收货地址DAO接口
 * @author stephen
 * @version 2019-10-25
 */
@MyBatisDao
public interface GroceryFansAddressDao extends CrudDao<GroceryFansAddress> {
	void settingAddressStatus(GroceryFansAddress groceryFansAddress);
}
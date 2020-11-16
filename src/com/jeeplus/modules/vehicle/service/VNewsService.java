/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.vehicle.dao.VNewsDao;
import com.jeeplus.modules.vehicle.entity.VNews;

/**
 * 新闻资讯Service
 * @author stephen
 * @version 2019-12-12
 */
@Service
@Transactional(readOnly = true)
public class VNewsService extends CrudService<VNewsDao, VNews> {
	
	public VNews get(String id) {
		return super.get(id);
	}
	
	public List<VNews> findList(VNews vNews) {
		return super.findList(vNews);
	}
	
	public Page<VNews> findPage(Page<VNews> page, VNews vNews) {
		return super.findPage(page, vNews);
	}
	
	@Transactional(readOnly = false)
	public void save(VNews vNews) {
		super.save(vNews);
	}
	
	@Transactional(readOnly = false)
	public void delete(VNews vNews) {
		super.delete(vNews);
	}
	
}
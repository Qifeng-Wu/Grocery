/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.vehicle.dao.VHelpDao;
import com.jeeplus.modules.vehicle.entity.VHelp;

/**
 * 帮助中心Service
 * @author stephen
 * @version 2019-12-12
 */
@Service
@Transactional(readOnly = true)
public class VHelpService extends CrudService<VHelpDao, VHelp> {
	
	public VHelp get(String id) {
		return super.get(id);
	}
	
	public List<VHelp> findList(VHelp vHelp) {
		return super.findList(vHelp);
	}
	
	public Page<VHelp> findPage(Page<VHelp> page, VHelp vHelp) {
		return super.findPage(page, vHelp);
	}
	
	@Transactional(readOnly = false)
	public void save(VHelp vHelp) {
		super.save(vHelp);
	}
	
	@Transactional(readOnly = false)
	public void delete(VHelp vHelp) {
		super.delete(vHelp);
	}
	
}
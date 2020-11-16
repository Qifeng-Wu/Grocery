/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.vehicle.dao.VFansDao;
import com.jeeplus.modules.vehicle.entity.VFans;

/**
 * 微信粉丝Service
 * @author stephen
 * @version 2019-10-25
 */
@Service
@Transactional(readOnly = true)
public class VFansService extends CrudService<VFansDao, VFans> {

	public VFans get(String id) {
		return super.get(id);
	}
	
	public List<VFans> findList(VFans vFans) {
		return super.findList(vFans);
	}
	
	public Page<VFans> findPage(Page<VFans> page, VFans vFans) {
		return super.findPage(page, vFans);
	}
	
	@Transactional(readOnly = false)
	public void save(VFans vFans) {
		super.save(vFans);
	}
	
	@Transactional(readOnly = false)
	public String customSave(VFans vFans) {
		boolean isNewRecord = true;
		if (vFans.getOpenId() != null){
			isNewRecord = false;		
		}
		vFans.setIsNewRecord(isNewRecord);
		super.saveCustomId(vFans);
		return vFans.getOpenId();
	}
	
	@Transactional(readOnly = false)
	public void delete(VFans vFans) {
		super.delete(vFans);
	}
}
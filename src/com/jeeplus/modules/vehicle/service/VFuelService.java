/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.vehicle.entity.VFuel;
import com.jeeplus.modules.vehicle.dao.VFuelDao;

/**
 * 加油记录Service
 * @author stephen
 * @version 2019-11-24
 */
@Service
@Transactional(readOnly = true)
public class VFuelService extends CrudService<VFuelDao, VFuel> {

	public VFuel get(String id) {
		return super.get(id);
	}
	
	public List<VFuel> findList(VFuel vFuel) {
		return super.findList(vFuel);
	}
	
	public Page<VFuel> findPage(Page<VFuel> page, VFuel vFuel) {
		return super.findPage(page, vFuel);
	}
	
	@Transactional(readOnly = false)
	public void save(VFuel vFuel) {
		super.save(vFuel);
	}
	
	@Transactional(readOnly = false)
	public Integer customSave(VFuel vFuel) {
		boolean isNewRecord = true;
		if (vFuel.getFuelId() != null){
			isNewRecord = false;		
		}
		vFuel.setIsNewRecord(isNewRecord);
		super.saveCustomId(vFuel);
		return vFuel.getFuelId();
	}
	
	@Transactional(readOnly = false)
	public void delete(VFuel vFuel) {
		super.delete(vFuel);
	}
	
}
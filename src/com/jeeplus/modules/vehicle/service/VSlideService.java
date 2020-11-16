/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.vehicle.dao.VSlideDao;
import com.jeeplus.modules.vehicle.entity.VSlide;

/**
 * 幻灯片Service
 * @author stephen
 * @version 2019-10-25
 */
@Service
@Transactional(readOnly = true)
public class VSlideService extends CrudService<VSlideDao, VSlide> {

	public VSlide get(String id) {
		return super.get(id);
	}
	
	public List<VSlide> findList(VSlide vSlide) {
		return super.findList(vSlide);
	}
	
	public Page<VSlide> findPage(Page<VSlide> page, VSlide vSlide) {
		return super.findPage(page, vSlide);
	}
	
	@Transactional(readOnly = false)
	public void save(VSlide vSlide) {
		super.save(vSlide);
	}
	
	@Transactional(readOnly = false)
	public Integer customSave(VSlide vSlide) {
		boolean isNewRecord = true;
		if (vSlide.getSlideId() != null){
			isNewRecord = false;		
		}
		vSlide.setIsNewRecord(isNewRecord);
		super.saveCustomId(vSlide);
		return vSlide.getSlideId();
	}
	
	@Transactional(readOnly = false)
	public void delete(VSlide vSlide) {
		super.delete(vSlide);
	}
	
}
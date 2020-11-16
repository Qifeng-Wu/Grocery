/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.animal.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.animal.dao.DWAnimalDao;
import com.jeeplus.modules.animal.entity.DWAnimal;

/**
 * 动物记录Service
 * @author stephen
 * @version 2020-11-09
 */
@Service
@Transactional(readOnly = true)
public class DWAnimalService extends CrudService<DWAnimalDao, DWAnimal> {

	public DWAnimal get(String id) {
		return super.get(id);
	}
	
	public List<DWAnimal> findList(DWAnimal dwAnimal) {
		return super.findList(dwAnimal);
	}
	
	public Page<DWAnimal> findPage(Page<DWAnimal> page, DWAnimal dwAnimal) {
		return super.findPage(page, dwAnimal);
	}
	
	@Transactional(readOnly = false)
	public void save(DWAnimal dwAnimal) {
		super.save(dwAnimal);
	}
	
	@Transactional(readOnly = false)
	public Integer customSave(DWAnimal dwAnimal) {
		boolean isNewRecord = true;
		if (dwAnimal.getAnimalId() != null){
			isNewRecord = false;		
		}
		dwAnimal.setIsNewRecord(isNewRecord);
		super.saveCustomId(dwAnimal);
		return dwAnimal.getAnimalId();
	}
	
	@Transactional(readOnly = false)
	public void delete(DWAnimal dwAnimal) {
		super.delete(dwAnimal);
	}
	
}
/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeeplus.common.persistence.Page;
import com.jeeplus.common.service.CrudService;
import com.jeeplus.modules.vehicle.dao.VFeedbackDao;
import com.jeeplus.modules.vehicle.entity.VFeedback;


/**
 * 客户意见反馈Service
 * @author stephen
 * @version 2019-10-25
 */
@Service
@Transactional(readOnly = true)
public class VFeedbackService extends CrudService<VFeedbackDao, VFeedback> {
		
	public VFeedback get(String id) {
		return super.get(id);
	}
	
	public List<VFeedback> findList(VFeedback vFeedback) {
		return super.findList(vFeedback);
	}
	
	public Page<VFeedback> findPage(Page<VFeedback> page, VFeedback vFeedback) {
		return super.findPage(page, vFeedback);
	}
	
	@Transactional(readOnly = false)
	public void save(VFeedback vFeedback) {
		super.save(vFeedback);
	}
	
	@Transactional(readOnly = false)
	public void delete(VFeedback vFeedback) {
		super.delete(vFeedback);
	}

}
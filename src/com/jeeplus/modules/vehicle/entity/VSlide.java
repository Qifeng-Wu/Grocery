/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.entity;


import com.jeeplus.common.persistence.DataEntity;

/**
 * 幻灯片Entity
 * @author stephen
 * @version 2019-11-25
 */
public class VSlide extends DataEntity<VSlide> {
	
	private static final long serialVersionUID = 1L;
	private Integer slideId;		// 主键ID
	private String title;	    	// 标题
	private String picture;	    //图片
	private String description;	    //幻灯片描述
	private Integer sort;    //排序
	private Boolean status;   	 	//是否启用，默认不启用（0：不启用，1：启用）	
	
	public VSlide() {
		super();
	}

	public VSlide(String id){
		super(id);
	}

	public Integer getSlideId() {
		return slideId;
	}

	public void setSlideId(Integer slideId) {
		this.slideId = slideId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	
}
/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.animal.entity;


import java.math.BigDecimal;
import java.util.Date;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.modules.vehicle.entity.VFans;

/**
 * 动物记录Entity
 * @author stephen
 * @version 2020-11-09
 */
public class DWAnimal extends DataEntity<DWAnimal> {
	
	private static final long serialVersionUID = 1L;
	private Integer animalId;		// 主键ID
	private String openId;			// 微信OPENID
	private String account;			// 所属账号
	private String name;			// 动物名称
	private String no;				// 动物编号
	private BigDecimal price;		// 价格
	private BigDecimal profit;		// 利润
	private Date startTime;			// 开始日期
	private Date endTime;			// 结束日期
	private Date createTime;		// 创建时间
	private String remark;			// 备注
	
	private VFans fans;  			//微信用户实体
	private Integer timeType;		// 时间类型（仅供接口传参按时间判断不同接口获取不同记录，在sql语句中做判断条件）
	
	public DWAnimal() {
		super();
	}

	public DWAnimal(String id){
		super(id);
	}

	public Integer getAnimalId() {
		return animalId;
	}

	public void setAnimalId(Integer animalId) {
		this.animalId = animalId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public VFans getFans() {
		return fans;
	}

	public void setFans(VFans fans) {
		this.fans = fans;
	}

	public Integer getTimeType() {
		return timeType;
	}

	public void setTimeType(Integer timeType) {
		this.timeType = timeType;
	}

}
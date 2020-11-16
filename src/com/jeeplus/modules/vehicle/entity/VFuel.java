/**
 * Copyright &copy; 2015-2020 <a href="http://www.jeeplus.org/">JeePlus</a> All rights reserved.
 */
package com.jeeplus.modules.vehicle.entity;

import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;

import com.jeeplus.common.persistence.DataEntity;
import com.jeeplus.common.utils.excel.annotation.ExcelField;

/**
 * 加油记录Entity
 * @author stephen
 * @version 2019-11-24
 */
public class VFuel extends DataEntity<VFuel> {
	
	private static final long serialVersionUID = 1L;
	private Integer fuelId;		// 主键
	private String openId;		// 微信OPENID
	private BigDecimal price;		// 燃油单价（元/升）
	private BigDecimal money;		// 加油金额（元）
	private Integer cmileage;	// 当前公里数
	private Integer rest;		// 当前公里数
	private String oil;		// 燃油标号
	private Date time;		// 加油时间
	private Date createtime;		// 创建时间
	private String remark;		// 备注
	
	private VFans fans;  //微信用户实体
	private Integer dmileage;	// 行驶公里数
	private BigDecimal consumption;	// 油耗
	
	public VFuel() {
		super();
	}

	public VFuel(String id){
		super(id);
	}

	@Length(min=1, max=11, message="主键长度必须介于 1 和 11 之间")
	@ExcelField(title="主键", align=2, sort=0)
	public Integer getFuelId() {
		return fuelId;
	}

	public void setFuelId(Integer fuelId) {
		this.fuelId = fuelId;
	}
	
	@Length(min=1, max=11, message="微信OPENID长度必须介于 1 和 11 之间")
	@ExcelField(title="微信OPENID", align=2, sort=1)
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}
	
	@ExcelField(title="燃油单价（元/升）", align=2, sort=2)
	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	@ExcelField(title="加油金额（元）", align=2, sort=3)
	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	
	@Length(min=0, max=11, message="当前公里数长度必须介于 0 和 11 之间")
	@ExcelField(title="当前公里数", align=2, sort=4)
	public Integer getCmileage() {
		return cmileage;
	}

	public void setCmileage(Integer cmileage) {
		this.cmileage = cmileage;
	}
	
	public Integer getRest() {
		return rest;
	}

	public void setRest(Integer rest) {
		this.rest = rest;
	}

	@Length(min=0, max=50, message="燃油标号长度必须介于 0 和 50 之间")
	@ExcelField(title="燃油标号", align=2, sort=5)
	public String getOil() {
		return oil;
	}

	public void setOil(String oil) {
		this.oil = oil;
	}
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="加油时间", align=2, sort=6)
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelField(title="创建时间", align=2, sort=7)
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	@Length(min=0, max=500, message="备注长度必须介于 0 和 500 之间")
	@ExcelField(title="备注", align=2, sort=8)
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

	public Integer getDmileage() {
		return dmileage;
	}

	public void setDmileage(Integer dmileage) {
		this.dmileage = dmileage;
	}

	public BigDecimal getConsumption() {
		return consumption;
	}

	public void setConsumption(BigDecimal consumption) {
		this.consumption = consumption;
	}
	
}
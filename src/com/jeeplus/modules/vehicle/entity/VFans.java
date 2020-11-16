package com.jeeplus.modules.vehicle.entity;

import java.util.Date;

import com.jeeplus.common.persistence.DataEntity;

/**
 * 微信信息Entity
 * @author stephen
 * @version 2019-11-25
 */
public class VFans extends DataEntity<VFans> {
	
	private static final long serialVersionUID = 1L;
	private String openId;		// 主键
	private String nickname;		// 昵称
	private String avatar;		// 头像
	private String sex;		// 性别（0：没有设置；1：男；2：女）
	private Date addtime;		// 添加时间
	private Date updatetime;		// 跟新时间
	
	public VFans() {
		super();
	}

	public VFans(String id){
		super(id);
	}
	
	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getUpdatetime() {
		return updatetime;
	}

	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}		

}
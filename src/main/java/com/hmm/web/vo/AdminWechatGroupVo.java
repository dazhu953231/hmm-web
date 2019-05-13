package com.hmm.web.vo;

import java.io.Serializable;

public class AdminWechatGroupVo implements Serializable {
	
	private static final long serialVersionUID = -1607638905611592346L;
	
	private Long wechatId;//微信id
	private Long groupId;//群id
	private Long platformListId;//发送列表id
	private Integer manageStatus;//1:不管理 2:管理
	private String message;//错误原因
	
	public Long getWechatId() {
		return wechatId;
	}
	public void setWechatId(Long wechatId) {
		this.wechatId = wechatId;
	}
	public Long getGroupId() {
		return groupId;
	}
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	public Long getPlatformListId() {
		return platformListId;
	}
	public void setPlatformListId(Long platformListId) {
		this.platformListId = platformListId;
	}
	public Integer getManageStatus() {
		return manageStatus;
	}
	public void setManageStatus(Integer manageStatus) {
		this.manageStatus = manageStatus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}

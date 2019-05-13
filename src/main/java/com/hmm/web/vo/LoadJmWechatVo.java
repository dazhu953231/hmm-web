package com.hmm.web.vo;

import java.io.Serializable;

public class LoadJmWechatVo implements Serializable {
	
	private static final long serialVersionUID = -1761930136952724851L;
	
	private String deviceNo; //设备号
	private String jmId;//极光id
	private String wechatUin; //微信id
	private String wechatName; //微信名称
	
	public String getDeviceNo() {
		return deviceNo;
	}
	public void setDeviceNo(String deviceNo) {
		this.deviceNo = deviceNo;
	}
	public String getJmId() {
		return jmId;
	}
	public void setJmId(String jmId) {
		this.jmId = jmId;
	}
	public String getWechatUin() {
		return wechatUin;
	}
	public void setWechatUin(String wechatUin) {
		this.wechatUin = wechatUin;
	}
	public String getWechatName() {
		return wechatName;
	}
	public void setWechatName(String wechatName) {
		this.wechatName = wechatName;
	}
	
}

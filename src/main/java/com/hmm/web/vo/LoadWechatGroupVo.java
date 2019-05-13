package com.hmm.web.vo;

import java.io.Serializable;
import java.util.List;

public class LoadWechatGroupVo implements Serializable {
	
	private static final long serialVersionUID = -111951605520319580L;
	
	private String wechatUin; //微信id
	private List<LoadGroupVo> baseGroups;//群集合
	
	public String getWechatUin() {
		return wechatUin;
	}
	public void setWechatUin(String wechatUin) {
		this.wechatUin = wechatUin;
	}
	public List<LoadGroupVo> getBaseGroups() {
		return baseGroups;
	}
	public void setBaseGroups(List<LoadGroupVo> baseGroups) {
		this.baseGroups = baseGroups;
	}
	
}

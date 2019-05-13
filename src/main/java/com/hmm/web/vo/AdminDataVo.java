package com.hmm.web.vo;

import java.io.Serializable;
import java.util.List;

public class AdminDataVo implements Serializable {
	
	private static final long serialVersionUID = -6497350342238845308L;
	
	private List<AdminWechatGroupVo> adminWechatGroups;

	public List<AdminWechatGroupVo> getAdminWechatGroups() {
		return adminWechatGroups;
	}
	public void setAdminWechatGroups(List<AdminWechatGroupVo> adminWechatGroups) {
		this.adminWechatGroups = adminWechatGroups;
	}
	
}

package com.hmm.web.vo;

import java.io.Serializable;

public class LoadGroupVo implements Serializable {
	
	private static final long serialVersionUID = 8610947713719999869L;
	
	private String groupUin;
	private String displayName;
	private String nickName;
	private Long groupMembers;
	
	public String getGroupUin() {
		return groupUin;
	}
	public void setGroupUin(String groupUin) {
		this.groupUin = groupUin;
	}
	public String getDisplayName() {
		return displayName;
	}
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Long getGroupMembers() {
		return groupMembers;
	}
	public void setGroupMembers(Long groupMembers) {
		this.groupMembers = groupMembers;
	}
	
}

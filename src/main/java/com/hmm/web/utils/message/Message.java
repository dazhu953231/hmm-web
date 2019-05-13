package com.hmm.web.utils.message;

import java.io.Serializable;

import com.hmm.web.domain.SendListContent;

public class Message implements Serializable{
	
	private static final long serialVersionUID = 1760696185667060431L;
	
	private String groupUin;
	private SendListContent sendListContent;
	
	public String getGroupUin() {
		return groupUin;
	}
	public void setGroupUin(String groupUin) {
		this.groupUin = groupUin;
	}
	public SendListContent getSendListContent() {
		return sendListContent;
	}
	public void setSendListContent(SendListContent sendListContent) {
		this.sendListContent = sendListContent;
	}
	
}

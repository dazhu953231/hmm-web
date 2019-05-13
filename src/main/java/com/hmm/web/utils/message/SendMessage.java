package com.hmm.web.utils.message;

import java.io.Serializable;
import java.util.Set;

public class SendMessage extends SendContent implements Serializable {
	
	private static final long serialVersionUID = -5657113501642887246L;
	
	private Set<Message> messages;//消息列表
	
	public Set<Message> getMessages() {
		return messages;
	}
	public void setMessages(Set<Message> messages) {
		this.messages = messages;
	}
}

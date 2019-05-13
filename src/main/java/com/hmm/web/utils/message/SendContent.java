package com.hmm.web.utils.message;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class SendContent {
	
	@JsonIgnore
	private long jmMessageId;

	public long getJmMessageId() {
		return jmMessageId;
	}
	public void setJmMessageId(long jmMessageId) {
		this.jmMessageId = jmMessageId;
	}
	
}

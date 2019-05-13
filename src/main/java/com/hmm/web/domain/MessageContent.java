package com.hmm.web.domain;

import java.io.Serializable;

public class MessageContent implements Serializable {
    private Long id;

    private Long messageJmId;

    private String jmId;

    private String msgContent;
    
    /**
     * 状态 0:不可用 1:可用
     */
    private Integer status;
    
    private Long updateTime;

    private Long createTime;
    
    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMessageJmId() {
        return messageJmId;
    }

    public void setMessageJmId(Long messageJmId) {
        this.messageJmId = messageJmId;
    }

    public String getJmId() {
        return jmId;
    }

    public void setJmId(String jmId) {
        this.jmId = jmId == null ? null : jmId.trim();
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }

    public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Long updateTime) {
		this.updateTime = updateTime;
	}

	public Long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", messageJmId=").append(messageJmId);
        sb.append(", jmId=").append(jmId);
        sb.append(", msgContent=").append(msgContent);
        sb.append(", status=").append(status);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
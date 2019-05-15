package com.hmm.web.vo;

import java.io.Serializable;

public class SendPlanVo implements Serializable{
	
	private static final long serialVersionUID = 3367851987352518942L;
	
    private Long startTime;//开始时间
    private Long endTime;//结束时间
    private Integer messageSendInterval;//发送间隔
    private Long platformListId;//发送列表
    private String planComment;//计划备注
    private Integer status;//0:已完成 1:待执行 2:执行中
    
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public Integer getMessageSendInterval() {
		return messageSendInterval;
	}
	public void setMessageSendInterval(Integer messageSendInterval) {
		this.messageSendInterval = messageSendInterval;
	}
	public Long getPlatformListId() {
		return platformListId;
	}
	public void setPlatformListId(Long platformListId) {
		this.platformListId = platformListId;
	}
	public String getPlanComment() {
		return planComment;
	}
	public void setPlanComment(String planComment) {
		this.planComment = planComment;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
    
}

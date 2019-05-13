package com.hmm.web.domain;

import java.io.Serializable;

public class SendPlan implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 计划开始时间
     */
    private Long startTime;

    /**
     * 计划结束时间
     */
    private Long endTime;

    /**
     * 发送时间间隔
     */
    private Integer messageSendInterval;

    /**
     * 发送列表id
     */
    private Long platformListId;

    /**
     * 计划备注
     */
    private String planComment;

    /**
     * 状态0:已完成 1:待执行 2:执行中
     */
    private Integer status;

    /**
     * 修改时间
     */
    private Long updateTime;

    /**
     * 创建时间
     */
    private Long createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
        this.planComment = planComment == null ? null : planComment.trim();
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
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", messageSendInterval=").append(messageSendInterval);
        sb.append(", platformListId=").append(platformListId);
        sb.append(", planComment=").append(planComment);
        sb.append(", status=").append(status);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
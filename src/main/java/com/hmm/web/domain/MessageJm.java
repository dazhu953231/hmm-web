package com.hmm.web.domain;

import java.io.Serializable;

public class MessageJm implements Serializable {
    private Long id;

    /**
     * 极光定时id
     */
    private String jmScheduleId;

    /**
     * 发送计划id
     */
    private Long platformPlanId;

    /**
     * 单条内容发送量
     */
    private Long messageCount;

    /**
     * 发送列表id
     */
    private Long platformListId;

    /**
     * 发送内容id
     */
    private Long platformContentId;

    /**
     * 发送时间
     */
    private Long sendTime;

    /**
     * 0已发送 1未发送, -状态针对极光
     */
    private Integer status;

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

    public String getJmScheduleId() {
        return jmScheduleId;
    }

    public void setJmScheduleId(String jmScheduleId) {
        this.jmScheduleId = jmScheduleId == null ? null : jmScheduleId.trim();
    }

    public Long getPlatformPlanId() {
        return platformPlanId;
    }

    public void setPlatformPlanId(Long platformPlanId) {
        this.platformPlanId = platformPlanId;
    }

    public Long getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Long messageCount) {
        this.messageCount = messageCount;
    }

    public Long getPlatformListId() {
        return platformListId;
    }

    public void setPlatformListId(Long platformListId) {
        this.platformListId = platformListId;
    }

    public Long getPlatformContentId() {
        return platformContentId;
    }

    public void setPlatformContentId(Long platformContentId) {
        this.platformContentId = platformContentId;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
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
        sb.append(", jmScheduleId=").append(jmScheduleId);
        sb.append(", platformPlanId=").append(platformPlanId);
        sb.append(", messageCount=").append(messageCount);
        sb.append(", platformListId=").append(platformListId);
        sb.append(", platformContentId=").append(platformContentId);
        sb.append(", sendTime=").append(sendTime);
        sb.append(", status=").append(status);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
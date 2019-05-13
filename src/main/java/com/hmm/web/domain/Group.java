package com.hmm.web.domain;

import java.io.Serializable;

public class Group implements Serializable {
    /**
     * 微信群主键id
     */
    private Long groupId;

    /**
     * 微信群uin
     */
    private String groupUin;

    /**
     * 微信群人数
     */
    private Long groupMembers;

    /**
     * 发送列表id
     */
    private Long platformListId;

    /**
     * 发送列表名称
     */
    private String platformListName;

    /**
     * 状态 0：异常 1：正常:2被管理
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

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupUin() {
        return groupUin;
    }

    public void setGroupUin(String groupUin) {
        this.groupUin = groupUin == null ? null : groupUin.trim();
    }

    public Long getGroupMembers() {
        return groupMembers;
    }

    public void setGroupMembers(Long groupMembers) {
        this.groupMembers = groupMembers;
    }

    public Long getPlatformListId() {
        return platformListId;
    }

    public void setPlatformListId(Long platformListId) {
        this.platformListId = platformListId;
    }

    public String getPlatformListName() {
        return platformListName;
    }

    public void setPlatformListName(String platformListName) {
        this.platformListName = platformListName == null ? null : platformListName.trim();
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
        sb.append(", groupId=").append(groupId);
        sb.append(", groupUin=").append(groupUin);
        sb.append(", groupMembers=").append(groupMembers);
        sb.append(", platformListId=").append(platformListId);
        sb.append(", platformListName=").append(platformListName);
        sb.append(", status=").append(status);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
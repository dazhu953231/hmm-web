package com.hmm.web.domain;

import java.io.Serializable;

public class WechatGroupRelate implements Serializable {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 微信id
     */
    private Long wechatId;

    /**
     * 微信uin
     */
    private String wechatUin;

    /**
     * 微信群id
     */
    private Long groupId;

    /**
     * 微信群uin
     */
    private String groupUin;

    /**
     * 状态 0:不可用 1:可用2被管理
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

    public Long getWechatId() {
        return wechatId;
    }

    public void setWechatId(Long wechatId) {
        this.wechatId = wechatId;
    }

    public String getWechatUin() {
        return wechatUin;
    }

    public void setWechatUin(String wechatUin) {
        this.wechatUin = wechatUin == null ? null : wechatUin.trim();
    }

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
        sb.append(", wechatId=").append(wechatId);
        sb.append(", wechatUin=").append(wechatUin);
        sb.append(", groupId=").append(groupId);
        sb.append(", groupUin=").append(groupUin);
        sb.append(", status=").append(status);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
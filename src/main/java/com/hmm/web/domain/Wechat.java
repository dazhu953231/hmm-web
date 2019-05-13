package com.hmm.web.domain;

import java.io.Serializable;

public class Wechat implements Serializable {
    /**
     * 微信主键id
     */
    private Long wechatId;

    /**
     * 微信uin
     */
    private String wechatUin;

    /**
     * 设备id
     */
    private Long deviceId;

    /**
     * 状态 0: 离线 1:在线
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

    /**
     * 微信名称
     */
    private byte[] wechatName;

    private static final long serialVersionUID = 1L;

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

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
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

    public byte[] getWechatName() {
        return wechatName;
    }

    public void setWechatName(byte[] wechatName) {
        this.wechatName = wechatName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wechatId=").append(wechatId);
        sb.append(", wechatUin=").append(wechatUin);
        sb.append(", deviceId=").append(deviceId);
        sb.append(", status=").append(status);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", wechatName=").append(wechatName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
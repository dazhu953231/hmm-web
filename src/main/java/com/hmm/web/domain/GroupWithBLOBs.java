package com.hmm.web.domain;

import java.io.Serializable;

public class GroupWithBLOBs extends Group implements Serializable {
    /**
     * 微信群展示名
     */
    private byte[] displayName;

    /**
     * 微信群呢名
     */
    private byte[] nickName;

    private static final long serialVersionUID = 1L;

    public byte[] getDisplayName() {
        return displayName;
    }

    public void setDisplayName(byte[] displayName) {
        this.displayName = displayName;
    }

    public byte[] getNickName() {
        return nickName;
    }

    public void setNickName(byte[] nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", displayName=").append(displayName);
        sb.append(", nickName=").append(nickName);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
package com.hmm.web.dao;

import com.hmm.web.domain.Wechat;

public interface WechatMapper {

    int insertSelective(Wechat record);

    Wechat selectByPrimaryKey(Long wechatId);

    int updateByPrimaryKeySelective(Wechat record);
    
    Wechat selectByWechatUin(String wechatUin);
    
}
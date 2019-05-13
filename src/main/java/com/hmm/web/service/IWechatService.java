package com.hmm.web.service;

import com.hmm.web.domain.Wechat;

public interface IWechatService {

    int insertSelective(Wechat record);

    Wechat selectByPrimaryKey(Long wechatId);

    int updateByPrimaryKeySelective(Wechat record);

    Wechat selectByWechatUin(String wechatUin);
    
}
package com.hmm.web.service;

import java.util.List;
import java.util.Map;

import com.hmm.web.domain.WechatGroupRelate;

public interface IWechatGroupRelateService {

    int insertSelective(WechatGroupRelate record);

    WechatGroupRelate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WechatGroupRelate record);

    List<String> selectGroupUinByMap(Map<String,Object> map);
    
    List<Long> selectIdByGroupIdNormal(Long groupId);
    
    WechatGroupRelate selectByWechatIdGroupIdNormal(Long wechatId,Long groupId);
    
}
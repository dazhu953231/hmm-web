package com.hmm.web.dao;

import java.util.List;
import java.util.Map;

import com.hmm.web.domain.WechatGroupRelate;

public interface WechatGroupRelateMapper {

    int insertSelective(WechatGroupRelate record);

    WechatGroupRelate selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WechatGroupRelate record);
    
	//TODO
    List<String> selectGroupUinByMap(Map<String,Object> map);
    
	//TODO
    List<Long> selectIdByGroupIdNormal(Long groupId);
    
	//TODO
    WechatGroupRelate selectByWechatIdGroupIdNormal(Long wechatId,Long groupId);
    
}
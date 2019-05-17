package com.hmm.web.dao;

import java.util.List;
import java.util.Map;

import com.hmm.web.domain.MessageContent;

public interface MessageContentMapper {
    
	int insertSelective(MessageContent record);

	MessageContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageContent record);
    
    List<MessageContent> selectByMap(Map<String,Object> map);
    
    void updateStatusByIds(List<Long> ids);
    
}
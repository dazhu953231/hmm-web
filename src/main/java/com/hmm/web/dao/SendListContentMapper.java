package com.hmm.web.dao;

import com.hmm.web.domain.SendListContent;

public interface SendListContentMapper {

    int insertSelective(SendListContent record);

    SendListContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SendListContent record);

    SendListContent selectTopOne(Long sendListId);
    
}
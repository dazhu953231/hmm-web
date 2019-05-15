package com.hmm.web.service;

import com.hmm.web.domain.SendListContent;

public interface ISendListContentService {

    int insertSelective(SendListContent record);

    SendListContent selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SendListContent record);

    SendListContent selectTopOne(Long sendListId);
    
}
package com.hmm.web.service;

import com.hmm.web.domain.SendList;

public interface ISendListService {

    int insertSelective(SendList record);

    SendList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SendList record);

}
package com.hmm.web.dao;

import com.hmm.web.domain.SendList;

public interface SendListMapper {

    int insertSelective(SendList record);

    SendList selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SendList record);

}
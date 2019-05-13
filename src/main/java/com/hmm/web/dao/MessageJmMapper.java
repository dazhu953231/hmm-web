package com.hmm.web.dao;

import com.hmm.web.domain.MessageJm;

public interface MessageJmMapper {

    int insertSelective(MessageJm record);

    MessageJm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageJm record);

}
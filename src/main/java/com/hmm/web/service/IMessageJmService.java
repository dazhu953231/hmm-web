package com.hmm.web.service;

import com.hmm.web.domain.MessageJm;

public interface IMessageJmService {

    int insertSelective(MessageJm record);

    MessageJm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MessageJm record);

}
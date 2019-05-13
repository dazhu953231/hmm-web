package com.hmm.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hmm.web.dao.MessageJmMapper;
import com.hmm.web.domain.MessageJm;
import com.hmm.web.service.IMessageJmService;
import com.hmm.web.utils.DateUtils;

@Component
public class MessageJmServiceImpl implements IMessageJmService {
	
	@Autowired
	private MessageJmMapper messageJmMapper;

	public int insertSelective(MessageJm record) {
		record.setStatus(1);
		record.setCreateTime(DateUtils.currentTimeSecs());
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return messageJmMapper.insertSelective(record);
	}

	public MessageJm selectByPrimaryKey(Long id) {
		return messageJmMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(MessageJm record) {
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return messageJmMapper.updateByPrimaryKeySelective(record);
	}

}

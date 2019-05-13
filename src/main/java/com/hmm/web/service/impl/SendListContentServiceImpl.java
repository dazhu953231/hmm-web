package com.hmm.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hmm.web.dao.SendListContentMapper;
import com.hmm.web.domain.SendListContent;
import com.hmm.web.service.ISendListContentService;
import com.hmm.web.utils.DateUtils;

@Component
public class SendListContentServiceImpl implements ISendListContentService {
	
	@Autowired
	private SendListContentMapper sendListContentMapper;

	public int insertSelective(SendListContent record) {
		record.setStatus(1);
		record.setCreateTime(DateUtils.currentTimeSecs());
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return sendListContentMapper.insertSelective(record);
	}

	public SendListContent selectByPrimaryKey(Long id) {
		return sendListContentMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(SendListContent record) {
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return sendListContentMapper.updateByPrimaryKeySelective(record);
	}

}

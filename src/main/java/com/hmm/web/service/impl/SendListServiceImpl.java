package com.hmm.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hmm.web.dao.SendListMapper;
import com.hmm.web.domain.SendList;
import com.hmm.web.service.ISendListService;
import com.hmm.web.utils.DateUtils;

@Component
public class SendListServiceImpl implements ISendListService {
	
	@Autowired
	private SendListMapper sendListMapper;

	public int insertSelective(SendList record) {
		record.setStatus(1);
		record.setCreateTime(DateUtils.currentTimeSecs());
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return sendListMapper.insertSelective(record);
	}

	public SendList selectByPrimaryKey(Long id) {
		return sendListMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(SendList record) {
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return sendListMapper.updateByPrimaryKeySelective(record);
	}

}

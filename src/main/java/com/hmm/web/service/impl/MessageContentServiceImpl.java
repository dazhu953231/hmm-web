package com.hmm.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hmm.web.dao.MessageContentMapper;
import com.hmm.web.domain.MessageContent;
import com.hmm.web.service.IMessageContentService;
import com.hmm.web.utils.DateUtils;

@Component
public class MessageContentServiceImpl implements IMessageContentService {
	
	@Autowired
	private MessageContentMapper messageContentMapper;

	public int insertSelective(MessageContent record) {
		record.setStatus(1);
		record.setCreateTime(DateUtils.currentTimeSecs());
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return messageContentMapper.insertSelective(record);
	}

	public MessageContent selectByPrimaryKey(Long id) {
		return messageContentMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(MessageContent record) {
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return messageContentMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<MessageContent> selectByMap(Map<String, Object> map) {
		return messageContentMapper.selectByMap(map);
	}

	@Override
	public void updateStatusByIds(List<Long> ids) {
		messageContentMapper.updateStatusByIds(ids);
	}

}

package com.hmm.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hmm.web.dao.SendPlanMapper;
import com.hmm.web.domain.SendPlan;
import com.hmm.web.service.ISendPlanService;
import com.hmm.web.utils.DateUtils;

@Component
public class SendPlanServiceImpl implements ISendPlanService {
	
	@Autowired
	private SendPlanMapper sendPlanMapper;

	public int insertSelective(SendPlan record) {
		record.setStatus(1);
		record.setCreateTime(DateUtils.currentTimeSecs());
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return sendPlanMapper.insertSelective(record);
	}

	public SendPlan selectByPrimaryKey(Long id) {
		return sendPlanMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(SendPlan record) {
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return sendPlanMapper.updateByPrimaryKeySelective(record);
	}

}

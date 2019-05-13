package com.hmm.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hmm.web.dao.WechatMapper;
import com.hmm.web.domain.Wechat;
import com.hmm.web.service.IWechatService;
import com.hmm.web.utils.DateUtils;

@Component
public class WechatServiceImpl implements IWechatService {
	
	@Autowired
	private WechatMapper wechatMapper;

	public int insertSelective(Wechat record) {
		record.setStatus(1);
		record.setCreateTime(DateUtils.currentTimeSecs());
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return wechatMapper.insertSelective(record);
	}

	public Wechat selectByPrimaryKey(Long wechatId) {
		return wechatMapper.selectByPrimaryKey(wechatId);
	}

	public int updateByPrimaryKeySelective(Wechat record) {
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return wechatMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Wechat selectByWechatUin(String wechatUin) {
		return wechatMapper.selectByWechatUin(wechatUin);
	}

}

package com.hmm.web.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hmm.web.dao.WechatGroupRelateMapper;
import com.hmm.web.domain.WechatGroupRelate;
import com.hmm.web.service.IWechatGroupRelateService;
import com.hmm.web.utils.DateUtils;


@Component
public class WechatGroupRelateServiceImpl implements IWechatGroupRelateService {
	
	@Autowired
	private WechatGroupRelateMapper wechatGroupRelateMapper;

	public int insertSelective(WechatGroupRelate record) {
		record.setStatus(1);
		record.setCreateTime(DateUtils.currentTimeSecs());
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return wechatGroupRelateMapper.insertSelective(record);
	}

	public WechatGroupRelate selectByPrimaryKey(Long id) {
		return wechatGroupRelateMapper.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(WechatGroupRelate record) {
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return wechatGroupRelateMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public List<String> selectGroupUinByMap(Map<String, Object> map) {
		return wechatGroupRelateMapper.selectGroupUinByMap(map);
	}

	@Override
	public List<Long> selectIdByGroupIdNormal(Long groupId) {
		return wechatGroupRelateMapper.selectIdByGroupIdNormal(groupId);
	}

	@Override
	public WechatGroupRelate selectByWechatIdGroupIdNormal(Long wechatId, Long groupId) {
		return wechatGroupRelateMapper.selectByWechatIdGroupIdNormal(wechatId,groupId);
	}

}

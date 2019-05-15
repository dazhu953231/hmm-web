package com.hmm.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hmm.web.dao.GroupMapper;
import com.hmm.web.domain.GroupWithBLOBs;
import com.hmm.web.service.IGroupService;
import com.hmm.web.utils.DateUtils;

@Component
public class GroupServiceImpl implements IGroupService {
	
	@Autowired
	private GroupMapper groupMapper;

	public int insertSelective(GroupWithBLOBs record) {
		record.setStatus(1);
		record.setCreateTime(DateUtils.currentTimeSecs());
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return groupMapper.insertSelective(record);
	}

	public GroupWithBLOBs selectByPrimaryKey(Long groupId) {
		return groupMapper.selectByPrimaryKey(groupId);
	}

	public int updateByPrimaryKeySelective(GroupWithBLOBs record) {
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return groupMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public GroupWithBLOBs selectByGroupUin(String groupUin) {
		return groupMapper.selectByGroupUin(groupUin);
	}

	@Override
	public List<Long> selectGroupIdByPlatListId(Long platListId) {
		return groupMapper.selectGroupIdByPlatListId(platListId);
	}

}

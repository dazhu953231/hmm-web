package com.hmm.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hmm.web.dao.UserMapper;
import com.hmm.web.domain.User;
import com.hmm.web.service.IUserService;
import com.hmm.web.utils.DateUtils;

@Component
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserMapper userMapper;

	public int insertSelective(User record) {
		record.setStatus(1);
		record.setCreateTime(DateUtils.currentTimeSecs());
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return userMapper.insertSelective(record);
	}

	public User selectByPrimaryKey(Long userId) {
		return userMapper.selectByPrimaryKey(userId);
	}

	public int updateByPrimaryKeySelective(User record) {
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return userMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public User selectByTelephonePassword(String telephone, String password) {
		return userMapper.selectByTelephonePassword(telephone,password);
	}

}

package com.hmm.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hmm.web.dao.DeviceMapper;
import com.hmm.web.domain.Device;
import com.hmm.web.service.IDeviceService;
import com.hmm.web.utils.DateUtils;

@Component
public class DeviceServiceImpl implements IDeviceService {
	
	@Autowired
	private DeviceMapper deviceMapper;

	public int insertSelective(Device record) {
		record.setStatus(1);
		record.setCreateTime(DateUtils.currentTimeSecs());
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return deviceMapper.insertSelective(record);
	}

	public Device selectByPrimaryKey(Long deviceId) {
		return deviceMapper.selectByPrimaryKey(deviceId);
	}

	public int updateByPrimaryKeySelective(Device record) {
		record.setUpdateTime(DateUtils.currentTimeSecs());
		return deviceMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Device selectByDeviceNo(String deviceNo) {
		return deviceMapper.selectByDeviceNo(deviceNo);
	}

}

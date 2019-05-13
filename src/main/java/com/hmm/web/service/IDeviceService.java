package com.hmm.web.service;

import com.hmm.web.domain.Device;

public interface IDeviceService {

    int insertSelective(Device record);

    Device selectByPrimaryKey(Long deviceId);

    int updateByPrimaryKeySelective(Device record);

    Device selectByDeviceNo(String deviceNo);
    
}
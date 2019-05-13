package com.hmm.web.dao;

import com.hmm.web.domain.Device;

public interface DeviceMapper {

    int insertSelective(Device record);

    Device selectByPrimaryKey(Long deviceId);

    int updateByPrimaryKeySelective(Device record);
    
    //TODO
    Device selectByDeviceNo(String deviceNo);
    
}
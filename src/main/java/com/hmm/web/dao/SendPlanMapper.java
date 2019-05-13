package com.hmm.web.dao;

import com.hmm.web.domain.SendPlan;

public interface SendPlanMapper {

    int insertSelective(SendPlan record);

    SendPlan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SendPlan record);

}
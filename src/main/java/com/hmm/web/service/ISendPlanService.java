package com.hmm.web.service;

import com.hmm.web.domain.SendPlan;

public interface ISendPlanService {

    int insertSelective(SendPlan record);

    SendPlan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SendPlan record);

}
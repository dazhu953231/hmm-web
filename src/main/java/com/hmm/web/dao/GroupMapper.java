package com.hmm.web.dao;

import com.hmm.web.domain.GroupWithBLOBs;

public interface GroupMapper {

    int insertSelective(GroupWithBLOBs record);

    GroupWithBLOBs selectByPrimaryKey(Long groupId);

    int updateByPrimaryKeySelective(GroupWithBLOBs record);
    
	//TODO
    GroupWithBLOBs selectByGroupUin(String groupUin);
    
}
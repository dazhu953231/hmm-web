package com.hmm.web.service;

import java.util.List;

import com.hmm.web.domain.GroupWithBLOBs;

public interface IGroupService {

    int insertSelective(GroupWithBLOBs record);

    GroupWithBLOBs selectByPrimaryKey(Long groupId);

    int updateByPrimaryKeySelective(GroupWithBLOBs record);

    GroupWithBLOBs selectByGroupUin(String groupUin);
    
    List<Long> selectGroupIdByPlatListId(Long platListId);
    
}
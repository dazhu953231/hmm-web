package com.hmm.web.dao;

import com.hmm.web.domain.User;

public interface UserMapper {

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);
    
    User selectByTelephonePassword(String telephone, String password);
    
}
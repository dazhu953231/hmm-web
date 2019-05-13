package com.hmm.web.service;

import com.hmm.web.domain.User;

public interface IUserService {

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    User selectByTelephonePassword(String telephone,String password);
    
}
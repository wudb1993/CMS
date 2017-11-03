package com.cms.login.service.impl;

import com.cms.login.dao.UserLoginDao;
import com.cms.login.dto.User;
import com.cms.login.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 用户
 * @author  矜持的折返跑
 * @date 2017-11-02
 */
@Service("userLoginServiceImpl")
public class UserLoginServiceImpl implements UserLoginService {
    @Autowired
    private UserLoginDao userLoginDaoImpl;
    @Override
    public User userLogin(Map<String, Object> userLogin) {
      User userLoginInfo =  userLoginDaoImpl.userLoginInfo(userLogin);
        return userLoginInfo;
    }
}

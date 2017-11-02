package com.cms.login.service;

import com.cms.login.dto.User;

import java.util.Map;

/**
 * 用户
 * @author  矜持的折返跑
 * @date 2017-11-02
 */
public interface UserLoginService {
    /**
     * 登录
     * @param userLogin
     * @return
     */
    User userLogin(Map<String ,Object> userLogin);
}


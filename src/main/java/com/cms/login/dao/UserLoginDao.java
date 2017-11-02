package com.cms.login.dao;

import com.cms.base.dao.BaseDao;
import com.cms.login.dto.User;

import java.util.Map;

/**
 * 登录
 * @author 矜持的折返跑
 * @date 2017-11-02
 */
public interface UserLoginDao extends BaseDao<User,Long>{

    /**
     * 登录
     * @param userLogin
     * @return
     */
    User userLoginInfo(Map<String,Object> userLogin);

}

package com.cms.login.dao.impl;

import com.cms.base.dao.impl.BaseDaoImpl;
import com.cms.login.dao.UserLoginDao;
import com.cms.login.dto.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.Map;

/**
 * 登录
 * @author 矜持的折返跑
 * @date 2017-11-02
 */
@Repository("userLoginDaoImpl")
public class UserLoginDaoImpl extends BaseDaoImpl<User,Long> implements UserLoginDao{


    /**
     * 登录
     * @param userLogin
     * @return
     */
    @Override
    public User userLoginInfo(Map<String, Object> userLogin) {
        User userLoinInfo = getSqlSession().selectOne("user.userLogin",userLogin);
        return userLoinInfo;
    }
}

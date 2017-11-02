package com.cms.login.dao.impl;

import com.cms.base.dao.impl.BaseDaoImpl;
import com.cms.login.dao.UserLoginDao;
import com.cms.login.dto.User;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 登录
 * @author 矜持的折返跑
 * @date 2017-11-02
 */
@Repository("userLogin")
public class UserLoginDaoImpl extends BaseDaoImpl<User,Long> implements UserLoginDao{
    @Autowired
    private SqlSessionFactory sqlSessionFactory;


    /**
     * 登录
     * @param userLogin
     * @return
     */
    @Override
    public User userLoginInfo(Map<String, Object> userLogin) {
        User userLoinInfo = sqlSessionFactory.openSession().selectOne("user.userLogin",userLogin);
        return userLoinInfo;
    }
}

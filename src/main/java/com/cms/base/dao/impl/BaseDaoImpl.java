package com.cms.base.dao.impl;

import com.cms.base.dao.BaseDao;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;
import java.io.Serializable;

public abstract class BaseDaoImpl<T, PK extends Serializable> extends
        SqlSessionDaoSupport implements BaseDao<T, PK>{
    @Override
    @Resource(name = "sqlSessionFactory")
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
}

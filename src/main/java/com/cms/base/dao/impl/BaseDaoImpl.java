package com.cms.base.dao.impl;

import com.cms.base.dao.BaseDao;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.io.Serializable;

public abstract class BaseDaoImpl<T, PK extends Serializable> extends
        SqlSessionDaoSupport implements BaseDao<T, PK>{
}

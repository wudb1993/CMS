package com.notice.dao;

import com.notice.dto.SystemNotice;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository("systemNoticeDao")
public class SystemNoticeDao {
    @Resource(name = "sqlSessionFactory")
    private SqlSessionFactory sqlSessionFactory;

    public List<SystemNotice> systemNotice(Map<String,Object> conditions){
        List<SystemNotice> sysNoticeList = new ArrayList<SystemNotice>();


        return sysNoticeList;
    }
}

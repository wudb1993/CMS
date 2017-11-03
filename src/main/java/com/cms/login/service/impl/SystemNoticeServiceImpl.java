package com.cms.login.service.impl;

import com.cms.login.dao.SystemNoticeDao;
import com.cms.login.dto.Article;
import com.cms.login.service.SystemNoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 系统公告
 * @author 矜持的折返跑
 * @date 2017-11-03
 */
@Service(value="systemNoticeService")
public class SystemNoticeServiceImpl implements SystemNoticeService{
    @Autowired
    private SystemNoticeDao systemNoticeDao;

    /**
     * 查询系统公告
     * @param articelMap
     * @return
     */
    @Override
    public List<Article> queryArticel(Map<String,Object> articelMap) {
        List<Article> articleList = systemNoticeDao.queryNotice(articelMap);
        return articleList;
    }
}

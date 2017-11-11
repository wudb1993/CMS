package com.cms.login.dao;

import com.cms.base.dao.BaseDao;
import com.cms.login.dto.Article;

import java.util.List;
import java.util.Map;

/**
 * 系统公告
 * @author  矜持的折返跑
 * @date 2017-11-03
 */
public interface SystemNoticeDao extends BaseDao<Article,Long> {

    /**
     * 查询系统公告
     */
    List<Article> queryNotice(Map<String,Object> noticeMap);

    /**
     * 新增文章
     * @param conditions
     * @return
     */
    int saveArticle(Map<String,Object> conditions);
}

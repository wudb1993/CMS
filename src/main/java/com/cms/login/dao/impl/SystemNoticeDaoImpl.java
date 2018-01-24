package com.cms.login.dao.impl;

import com.cms.base.dao.impl.BaseDaoImpl;
import com.cms.login.dao.SystemNoticeDao;
import com.cms.login.dto.Article;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 系统公告
 * @author 矜持的折返跑
 * @date 2017-11-03
 */
@Repository(value="systemNoticeDaoImpl")
public class SystemNoticeDaoImpl extends BaseDaoImpl<Article,Long> implements SystemNoticeDao{
    /**
     * 系统公告
     * @param noticeMap
     * @return
     */
    @Override
    public List<Article> queryNotice(Map<String, Object> noticeMap) {
       List<Article> articleList = getSqlSession().selectList("article.querySystemArticle",noticeMap);
        return articleList;
    }

    @Override
    public int saveArticle(Map<String,Object> conditions){
        int result = getSqlSession().insert("article.saveArticle",conditions);
        return  result;
    }

    /**
     * 更新文章内容
     * @param article
     * @return
     */
    @Override
    public int updateArticle(Article article) {
        int result  = getSqlSession().update("article.updateArticle",article);
        return result;
    }
}

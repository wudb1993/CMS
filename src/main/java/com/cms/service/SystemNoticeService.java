package com.cms.service;

import com.cms.login.dto.Article;

import java.util.List;
import java.util.Map;

/**
 * 系统公告
 * @author  矜持的折返跑
 * @date 2017-11-03
 */
public interface SystemNoticeService {

    /**
     * 查询系统公告
     * @param articleMap
     * @return
     */
    List<Article> queryArticle(Map<String, Object> articleMap);

    /**
     * 新增文章
     * @param conditions
     * @return
     */
    String saveArtice(Map<String,Object> conditions);

    /**
     * 更新文章内容
     * @param article
     * @return
     */
    String updateArticle(Article article);
}

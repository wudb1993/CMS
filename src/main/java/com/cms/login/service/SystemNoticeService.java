package com.cms.login.service;

import com.cms.login.dao.SystemNoticeDao;
import com.cms.login.dto.Article;
import org.springframework.beans.factory.annotation.Autowired;

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
    List<Article> queryArticle(Map<String,Object> articleMap);
}

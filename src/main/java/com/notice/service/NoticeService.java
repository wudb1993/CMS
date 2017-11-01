package com.notice.service;

import com.notice.dto.SystemNotice;

import java.util.List;

/**
 * 用于各种公告
 * @Time 2017-10-31
 * @author 矜持的折返跑
 */
public interface NoticeService {

    /**
     * 查询系统公告
     * @param noticeType
     * @return
     */
    public List<SystemNotice> systemNotice(String noticeType);
}

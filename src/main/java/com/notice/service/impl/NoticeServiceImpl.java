package com.notice.service.impl;

import com.notice.dao.SystemNoticeDao;
import com.notice.dto.SystemNotice;
import com.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  矜持的折返跑
 * @date 2017-10-31
 */
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private SystemNoticeDao systemNoticeDao;

    @Override
    public List<SystemNotice> systemNotice(String noticeType) {
        Map<String,Object> conditions = new HashMap<String,Object>();
        conditions.put("noticeType",noticeType);
        List<SystemNotice> systemList = systemNoticeDao.systemNotice(conditions);
        return systemList;
    }
}

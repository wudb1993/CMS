package com.notice.controller;

import com.notice.dto.SystemNotice;
import com.notice.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller("eee")
@RequestMapping(value="/notice")
public class SystemNoticeController {
    @Autowired
    private NoticeService noticeService;

    @RequestMapping(value="/systemNotice",method= RequestMethod.GET)
    @ResponseBody
    public ModelAndView systemNotice(@RequestParam String noticeType){
        ModelAndView mv = new ModelAndView();
        List<SystemNotice> systemNoticeList = noticeService.systemNotice(noticeType);
        return mv;
    }
}

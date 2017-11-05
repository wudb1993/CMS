package com.cms.login.controller;

import com.cms.login.dto.Article;
import com.cms.login.service.SystemNoticeService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统公告
 * @author  矜持的折返跑
 * @date 2017-11-03
 */
@Controller
@RequestMapping(value="/systemNotice")
public class SystemNoticeController {

    @Autowired
    private SystemNoticeService systemNoticeService;

    @RequestMapping(value="/queryArticle",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView queryArticel(@RequestParam String param){
        ModelAndView mv = new ModelAndView();
        JSONObject paramList = JSONObject.fromObject(param);
        String noticeType = paramList.getString("noticeType");
        String title = paramList.getString("title");
        String startTime = paramList.getString("startTime");
        String endTime = paramList.getString("endTime");
        Map<String,Object> articleMap = new HashMap<String,Object>();
        articleMap.put("noticeType",noticeType);
        articleMap.put("title",title.trim());
        List<Article> articleList = systemNoticeService.queryArticle(articleMap);
        mv.addObject("articleList",articleList);
        return mv;
    }
}

package com.cms.login.controller;

import com.cms.login.dto.Article;
import com.cms.service.SystemNoticeService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
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

    /**
     * 查询文章公告
     * @param param
     * @return
     */
    @RequestMapping(value="/queryArticle",method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView queryArticel(@RequestParam String param){
        ModelAndView mv = new ModelAndView();
        JSONObject paramList = JSONObject.fromObject(param);
        String noticeType = "";
        String title ="";
        String startTime = "";
        String endTime = "";
        if( paramList.get("noticeType")!=null) {
            noticeType = paramList.getString("noticeType");
        }
        if(paramList.get("title")!=null){
            title = paramList.getString("title");
        }
       if(paramList.get("startTime")!=null){
           startTime = paramList.getString("startTime");
       }
       if(paramList.get("endTime")!=null) {
           endTime = paramList.getString("endTime");
       }
        Map<String,Object> articleMap = new HashMap<String,Object>();
        articleMap.put("noticeType",noticeType);
        articleMap.put("title",title.trim());
        List<Article> articleList = systemNoticeService.queryArticle(articleMap);
        mv.addObject("articleList",articleList);
        return mv;
    }

    /**
     * 新增文章
     * @param param
     * @return
     */
    @RequestMapping(value="/saveArticle")
    @ResponseBody
    public ModelAndView saveArticle(@RequestParam String param) {
        ModelAndView mv = new ModelAndView();
        JSONObject params = JSONObject.fromObject(param);
        Map<String,Object> conditions = new HashMap<String,Object>();
        String title = "";
        String noticeType = "2";
        String status = "";
        String statusVip = "";
        String content = "";
        String general = "";
        if (params.get("title") != null &&! "".equals(params.get("title"))) {
            title = params.getString("title");
        }
//        if (params.get("noticeType") != null && "".equals(params.get("noticeType"))) {
//            noticeType = params.getString("noticeType");
//        }
        if (params.get("status") != null &&! "".equals(params.get("status"))) {
            //在这里规定状态码status 0 未审核 1：审核通过 2：审核通过开放浏览
            //                       3：申通通过会员浏览  4：未审核开放浏览  5：未审核会员浏览
            status = params.getString("status");
        }
        if (params.get("general") != null && !"".equals(params.get("general"))) {
            general = params.getString("general");
        }
        if(params.get("content")!=null&&!"".equals(params.getString("content"))){
            content = params.getString("content");
        }
        conditions.put("userId","1");
        conditions.put("author","矜持的折返跑");
        conditions.put("title",title);
        conditions.put("general",general);
        conditions.put("content",content);
        conditions.put("noticeType",noticeType);
        conditions.put("investTime",new Date());
        conditions.put("status",status);
        String result  = systemNoticeService.saveArtice(conditions);
        if(result.equals("success")){
            mv.addObject("result","successs");
        }else{
            mv.addObject("result","false");
        }
        return mv;
    }
}

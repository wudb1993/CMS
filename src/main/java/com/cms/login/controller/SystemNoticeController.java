package com.cms.login.controller;

import com.cms.login.dto.Article;
import com.cms.service.SystemNoticeService;
import net.sf.json.JSONObject;
import org.apache.commons.collections.map.HashedMap;
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
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/queryArticle", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView queryArticel(@RequestParam String param) {
        ModelAndView mv = new ModelAndView();
        JSONObject paramList = JSONObject.fromObject(param);
        String noticeType = paramList.optString("noticeType", "1");
        String id = paramList.optString("id");
        String title = paramList.optString("title");
        String startTime = paramList.optString("startTime");
        String endTime = paramList.optString("endTime");
        Map<String, Object> articleMap = new HashMap<String, Object>();
        articleMap.put("noticeType", noticeType);
        articleMap.put("title", title.trim());
        articleMap.put("id", id);
        List<Article> articleList = systemNoticeService.queryArticle(articleMap);
        mv.addObject("articleList", articleList);
        return mv;
    }

    /**
     * 新增文章
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/saveArticle")
    @ResponseBody
    public ModelAndView saveArticle(@RequestParam String param) {
        ModelAndView mv = new ModelAndView();
        JSONObject params = JSONObject.fromObject(param);
        Map<String, Object> conditions = new HashMap<String, Object>();
        String title = params.optString("title");
        String noticeType = params.optString("noticeType", "1");
        //在这里规定状态码status 0 未审核 1：审核通过 2：审核通过开放浏览
        //                       3：申通通过会员浏览  4：未审核开放浏览  5：未审核会员浏览
        String status = params.optString("status", "1");
        String general = params.optString("general");
        String content = params.optString("content");
        String permission = params.optString("permission", "1");
        conditions.put("userId", "1");
        conditions.put("author", "矜持的折返跑");
        conditions.put("title", title);
        conditions.put("general", general);
        conditions.put("content", content);
        conditions.put("noticeType", noticeType);
        conditions.put("investTime", new Date());
        conditions.put("status", status);
        conditions.put("permission", permission);
        String result = systemNoticeService.saveArtice(conditions);
        if (result.equals("success")) {
            mv.addObject("result", "successs");
        } else {
            mv.addObject("result", "false");
        }
        return mv;
    }

    @RequestMapping(value = "/updateArticleList")
    @ResponseBody
    public ModelAndView updateArticleList(@RequestParam String param){
        ModelAndView mv  = new ModelAndView();
        JSONObject params = JSONObject.fromObject(param);
        Map<String,Object> map =new  HashMap<String,Object>();
        if(params.get("articleId")!=null&&!"".equals(params.get("articleId"))){
            map.put("id", Long.valueOf(params.getString("articleId")));
        }else{
            mv.addObject("result","FAULT");
            mv.addObject("fault","请选择一篇文章后再操作");
            return mv;
        }
        List<Article> articleList = systemNoticeService.queryArticle(map);
        Article article = new Article();
        if(articleList.size()>0) {
            article = articleList.get(0);
            article.setStatus(params.getString("articleId"));
            article.setOperTime(new Date());
            article.setOperMan("矜持的折返跑");
            article.setOperId("1");
        }
        String result = systemNoticeService.updateArticle(article);
        if("SUCCESS".equals(result)){
            mv.addObject("result","SUCCESS");
            mv.addObject("fault","修改成功");

        }
        return mv;
    }

    /**
     * 更改整个文章的内容
     * @param param
     * @return
     */
    @RequestMapping(value = "/updateArticle")
    @ResponseBody
    public ModelAndView updateArticle(@RequestParam String param) {
        ModelAndView mv =new ModelAndView();
        JSONObject params = JSONObject.fromObject(param);
        String articleId = "";
        if(params.get("articleId")!=null&&!"".equals(params.get("articleId"))){
           articleId  = params.getString("articleId");
        }else{
            mv.addObject("result","FAULT");
            mv.addObject("fault","请选择一篇文章后再操作");
            return mv;
        }
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("id",articleId);
        List<Article> articleList = systemNoticeService.queryArticle(map);
        if(articleList.size()>0){
        Article articleOld = articleList.get(0);
            articleOld.setStatus(params.optString("status","1"));
            articleOld.setContent(params.optString("content"));
            articleOld.setGeneral(params.optString("general"));
            articleOld.setNoticeType(params.optString("noticeType","1"));
            articleOld.setOperId(params.optString("operId","1"));
            articleOld.setOperMan(params.optString("operMan","矜持的折返跑"));
            articleOld.setOperTime(new Date());
            articleOld.setPermission(params.optString("permission","1"));
            articleOld.setTitle(params.optString("title"));
            String result = systemNoticeService.updateArticle(articleOld);
            mv.addObject("result",result);
            mv.addObject("fault","修改成功！");
        }else{
            mv.addObject("result","FAULT");
            mv.addObject("fault","修改失败！");
        }

        return mv;
    }
}
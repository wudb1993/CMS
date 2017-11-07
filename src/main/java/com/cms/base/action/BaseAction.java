package com.cms.base.action;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;


import net.sf.json.JSONException;
import net.sf.json.JSONObject;


public class BaseAction {
    protected Logger log = LoggerFactory.getLogger(BaseAction.class);

    //	protected HttpServletRequest request;
    protected HttpServletResponse response;
//	protected HttpSession session;
//

    /**
     * 服务器端json
     */
    public transient String jsonParams;

    /**
     * 客户端json
     */
    public transient String clientJsonParams;

//    public String exportTempPath= ConfigFileHelper.getConfigProps().getProperty(ConfigFile.EXPORT_TEMP_PATH);

    @ModelAttribute
    public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
        this.response = response;
    }

    protected static HttpServletRequest getServletRequest() {
        return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
    }

    protected static HttpServletResponse getServletResponse(HttpServletRequest request) {
        HttpServletResponse resp = new ServletWebRequest( request ).getResponse();
        System.out.println(resp);
        return resp;
    }

    protected HttpServletResponse getServletResponse() {
//		HttpServletResponse resp = ((ServletWebRequest) RequestContextHolder.getRequestAttributes()).getResponse();
//		return resp;
        return response;
    }

    protected static HttpSession getServletSession() {
        return getServletRequest().getSession();
    }

    protected ServletContext getServletContext(){
        HttpServletRequest request = getServletRequest();
        if (request != null){
            return request.getSession().getServletContext();
        }

        return null;
    }
    /**
     * 返回json 对象的参数
     *
     * @return
     * @throws
     */
    public JSONObject getParams() {
        JSONObject jsonObject = null;
        JSONObject jo = null;
        try {
            // if (this.clientJsonParams != null &&
            // this.clientJsonParams.trim().length() != 0) {
            // jo = new JSONObject(this.clientJsonParams);
            // } else
            if (this.jsonParams != null && this.jsonParams.trim().length() != 0) {
                jo = JSONObject.fromObject(this.jsonParams);
            } else {
                jo = getParamsJSONObject();
            }
            if (null == jo) {
                return null;
            }
            if (jo.get("params") != null) {
                jsonObject = jo.getJSONObject("params");
            }
        }
        catch (JSONException e) {

        }
        return jsonObject;
    }

    // 处理前台不同的调用传值
    private JSONObject getParamsJSONObject()  {
        JSONObject jsonObject = null;
        try {
            if (this.jsonParams == null || this.jsonParams.trim().length() == 0) {// 如果取不到参数，那就用流的方式去取
                String jsonParams = getServletRequest().getParameter("jsonParams");// content-type
                jsonParams = deUnicodeString(jsonParams);
                // jsonParams
                if (jsonParams != null && jsonParams.trim().length() != 0) {
                    jsonObject = JSONObject.fromObject(jsonParams);
                    this.clientJsonParams = jsonObject.toString();
                    return jsonObject;
                }
            } else {
                jsonObject = JSONObject.fromObject(this.jsonParams);
            }
        }
        catch (Exception e) {
            log.error("System error:", e);
            if (e instanceof IOException) {

            } else if (e instanceof JSONException) {

            } else {

            }
        }
        return jsonObject;
    }

    protected String deUnicodeString(String str) {
        str = str.replace("&#34", "\'");
        str = str.replace("+", " ");
        str = str.replace("%20"," ") ;
        str = str.replace("%22","\"");
        str = str.replace("%23","#");
        str = str.replace("%25","%");
        str = str.replace("%26","&");
        str = str.replace("%28","(");
        str = str.replace("%29",")");
        str = str.replace("%2B","+");
        str = str.replace("%2C",",");
        str = str.replace("%2F","/");
        str = str.replace("%3A",":");
        str = str.replace("%3B",";");
        str = str.replace("%3C","<");
        str = str.replace("%3D","=");
        str = str.replace("%3E",">");
        str = str.replace("%3F","?");
        str = str.replace("%40","@");
        str = str.replace("%5C","\\");
        str = str.replace("%7C","|");

        return str;
    }

    protected String enUnicodeString(String str) {
        str = str.replace("\'", "&#34");
        str = str.replace(" ","%20") ;
        str = str.replace("\"","%22");
        str = str.replace("#","%23");
        str = str.replace("%","%25");
        str = str.replace("&","%26");
        str = str.replace("(","%28");
        str = str.replace(")","%29");
        str = str.replace("+","%2B");
        str = str.replace(",","%2C");
        str = str.replace("/","%2F");
        str = str.replace(":","%3A");
        str = str.replace(";","%3B");
        str = str.replace("<","%3C");
        str = str.replace("=","%3D");
        str = str.replace(">","%3E");
        str = str.replace("?","%3F");
        str = str.replace("@","%40");
        str = str.replace("\\","%5C");
        str = str.replace("|","%7C");

        return str;
    }



}

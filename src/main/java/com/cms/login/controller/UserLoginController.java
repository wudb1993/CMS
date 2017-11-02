package com.cms.login.controller;

import com.cms.login.dto.User;
import com.cms.login.service.UserLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * @author  矜持的折返跑
 * @date 2017-10-31
 */
@Controller
@RequestMapping(value="/user")
public class UserLoginController {

    @Autowired
    private UserLoginService userLoginService;
    @RequestMapping(value="/login")
    @ResponseBody
    public ModelAndView userLogin(@RequestParam String userName,@RequestParam String loginPwd){
        ModelAndView mv = new ModelAndView();

        Map<String,Object> userLoginMap = new HashMap<String,Object>();
        userLoginMap.put("userName",userName);
        userLoginMap.put("loginPwd",loginPwd);
        User user = userLoginService.userLogin(userLoginMap);
        mv.addObject("result","success");
        mv.addObject("userLoginInfo",user);

        return mv;

    }
}

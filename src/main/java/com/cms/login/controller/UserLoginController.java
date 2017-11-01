package com.cms.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author  矜持的折返跑
 * @date 2017-10-31
 */
@Controller
@RequestMapping(value="/user")
public class UserLoginController {

    @RequestMapping(value="/login")
    @ResponseBody
    public ModelAndView userLogin(@RequestParam String userName,@RequestParam String loginPwd){
        ModelAndView mv = new ModelAndView();

        mv.addObject("result","success");

        return mv;

    }
}

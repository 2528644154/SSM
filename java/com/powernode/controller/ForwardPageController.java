package com.powernode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*主要是完成页面跳转*/
@Controller
@RequestMapping("/forward")
public class ForwardPageController {

    //跳转到登录页面
    @RequestMapping("/toLogin.do")
    public String toLogin(){
        return "login";
    }

    //登录成功之后，跳转到后台主界面
    @RequestMapping("/toPage.do")
    public String toPage(String pageUrl){
        return pageUrl;
    }

}

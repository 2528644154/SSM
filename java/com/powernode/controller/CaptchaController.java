package com.powernode.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.GifCaptcha;
import com.powernode.common.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/captcha")
public class CaptchaController {

    @RequestMapping("/captcha.do")
    public void captcha(HttpServletResponse response, HttpSession session) throws IOException {
        GifCaptcha gifCaptcha = CaptchaUtil.createGifCaptcha(150,45,4);
        String code = gifCaptcha.getCode();
        //将code的值保存到session中，也就是说把一个叫code的数据打包成CODE_SESSION_KEY存在于session中
        session.setAttribute(Constants.CODE_SESSION_KEY,code);
        //将验证码写到页面去
        gifCaptcha.write(response.getOutputStream());
    }

}

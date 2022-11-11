package com.powernode.controller;

import com.powernode.common.Constants;
import com.powernode.common.Result;
import com.powernode.dto.AccountDto;
import com.powernode.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
@RequestMapping("/account")
@EnableTransactionManagement
public class AccountController {

    @Autowired
    private AccountService accountService;

    @RequestMapping("/getLogin.do")
    public Result getLogin(@Valid AccountDto accountDto, BindingResult br, HttpSession session){
        if(br.hasErrors()){
            return Result.DATA_FORMAT_ERROR;
        }
        return accountService.findLogin(accountDto,session);
    }

    //分页查询
    @RequestMapping("/getAccountsByPage.do")
    public Result getAccountsByPage(
        Integer pageNum,
        @RequestParam(required = false,defaultValue = "10") Integer pageSize
    ){
        return accountService.findAccountsByPage(pageNum,pageSize);
    }

    //退出
    @RequestMapping("/editLoginToOut.do")
    public Result editLoginToOut(HttpSession session){
        session.removeAttribute(Constants.USER_SESSION_KEY);
        return new Result();
    }

    /*添加新账号*/
    @RequestMapping("/addAccount.do")
    public Result addAccount(String username,HttpSession session){
        return accountService.saveAccount(username,session);
    }

    /*删除账号*/
    @RequestMapping("/cutOneAccount.do")
    public Result cutOneAccount(Long id,HttpSession session){
        return accountService.removeAccountById(id, session);
    }

    /*重置密码*/
    @RequestMapping("/editResetAccountPwd.do")
    public Result editResetAccountPwd(Long id,HttpSession session){
        return accountService.modifyResetAccountPwd(id,session);
    }

    /*启用与禁用*/
    @RequestMapping("/editAccountStatus.do")
    public Result editAccountStatus(Integer status,Long id,HttpSession session){
        return accountService.saveAccount(status,id,session);
    }

    /*修改密码*/
    @RequestMapping("/editAccountPwd.do")
    public Result editAccountPwd(String pwd,HttpSession session){
        return accountService.modifyAccountPwd(pwd,session);
    }

}

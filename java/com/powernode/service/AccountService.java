package com.powernode.service;

import com.powernode.common.Result;
import com.powernode.dto.AccountDto;

import javax.servlet.http.HttpSession;

public interface AccountService {

    /*登录*/
    Result findLogin(AccountDto accountDto, HttpSession session);

    /*分页查询*/
    /*AccountService处理的是AccountController里面的分页方法，AccountController里面的分页方法要与前端相对应*/
    Result findAccountsByPage(Integer pageNum,Integer pageSize);

    /*添加*/
    Result saveAccount(String username,HttpSession session);

    /*删除*/
    Result removeAccountById(Long id,HttpSession session);

    /*重置密码为123456*/
    Result modifyResetAccountPwd(Long id,HttpSession session);

    /*禁用或者启用*/
    Result saveAccount(Integer status,Long id,HttpSession session);

    /*上传头像*/
    Result modifyAccountImgUrl(String imgUrl, HttpSession session);

    /*修改密码*/
    Result modifyAccountPwd(String newPwd,HttpSession session);

}

package com.powernode.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.powernode.common.Constants;
import com.powernode.common.Result;
import com.powernode.domain.Account;
import com.powernode.dto.AccountDto;
import com.powernode.mapper.AccountMapper;
import com.powernode.service.AccountService;
import com.powernode.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements com.powernode.service.AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Result findLogin(AccountDto accountDto, HttpSession session) {
        //验证码
        String code = session.getAttribute(Constants.CODE_SESSION_KEY) + "";
        if(!(accountDto.getCaptcha().equals(code))){
            return new Result(-1,"验证码错误");
        }
        String encry = MD5Util.finalMD5(accountDto.getPwd());
        accountDto.setPwd(encry);
        Account account = accountMapper.selectByLogin(accountDto);
        if(account == null){
            return new Result(-1, "用户名或者密码错误");
        }
        //登录人
        session.setAttribute(Constants.USER_SESSION_KEY, account.getUsername());
        //登录角色
        session.setAttribute(Constants.ROLE_SESSION_KEY, account.getRole());
        //标记用户登录的id
        session.setAttribute(Constants.USER_SESSION_ID,account.getId());

        Result result = new Result();
        result.setData(account);
        return result;
    }

    @Override
    public Result findAccountsByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Account> accountList = accountMapper.selectAccountsByPage();
        PageInfo<Account> pageInfo = new PageInfo<>(accountList);

        Result result = new Result();
        result.setData(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Result saveAccount(String username,HttpSession session) {
        //只有超级管理员才有权限
        String role = session.getAttribute(Constants.ROLE_SESSION_KEY) + "";
        if(!"1".equals(role)){
            return new Result(-1,"你不是超级管理员，没有此操作权限");
        }
        //判断账号数据格式是否正确 [0-9a-zA-Z]
        if(username == null || !(username.matches("\\w{4,20}"))){
            return Result.DATA_FORMAT_ERROR;
        }
        //判断username这个账号在数据库中是否已经存在
        int n = accountMapper.selectUsernameIsExist(username);
        if(n == 1){
            return new Result(-1, "此账号已经存在，不能重复添加");
        }
        int i = accountMapper.insert(username);
        if(i == 0){
            return new Result(-1, "添加失败");
        }
        return new Result();
    }

    @Override
    public Result removeAccountById(Long id,HttpSession session) {
        //删除的角色：超级管理员
        String role = session.getAttribute(Constants.ROLE_SESSION_KEY) + "";
        if(!"1".equals(role)){
            return new Result(-1, "你不是超级管理员，没有此操作权限");
        }
        //判断是否是超级管理员要删除自己
        Object loginUserId = session.getAttribute(Constants.USER_SESSION_ID);
        if(id == Long.parseLong(loginUserId + "")){
            return new Result(-1, "超级管理员不能删除自己");
        }
        int i = accountMapper.deleteByPrimaryKey(id);
        if(i == 0){
            return new Result(-1, "删除失败");
        }
        return new Result();
    }

    @Override
    public Result modifyResetAccountPwd(Long id,HttpSession session) {
        //角色：超级管理员
        String role = session.getAttribute(Constants.ROLE_SESSION_KEY) + "";
        if(!"1".equals(role)){
            return new Result(-1, "你不是超级管理员，没有此操作权限");
        }
        //判断是否是超级管理员
        Long rootId = Long.parseLong(session.getAttribute(Constants.USER_SESSION_ID) + "");
        if(rootId == id){
            /*比如重置密码之前的登录密码是654321，重置完登录密码已改为123456，在重新登录之前，需要先把之前的654321的登录密码给删除掉*/
            session.removeAttribute(Constants.USER_SESSION_KEY);
        }
        int i = accountMapper.updateAccount(id);
        if(i == 0){
            return new Result(-1, "重置失败");
        }
        return new Result();
    }

    @Override
    public Result saveAccount(Integer status,Long id, HttpSession session) {
        //数据校验
        if(!(status == 0 || status == 1) || id <= 0){
            return Result.DATA_FORMAT_ERROR;
        }
        //判断此操作是否是超级管理员在执行
        String role = session.getAttribute(Constants.ROLE_SESSION_KEY) + "";
        if(!(role.equals("1"))){
            return new Result(-1, "你不是超级管理员，没有此操作权限");
        }
        int i = accountMapper.updateAccountStatus(status,id);
        if(i == 0){
            return new Result(-1, "修改记录不存在");
        }
        return new Result();
    }

    @Override
    public Result modifyAccountImgUrl(String imgUrl, HttpSession session) {
        String userId = session.getAttribute(Constants.USER_SESSION_ID) + "";
        int i = accountMapper.updateAccountImgUrl(imgUrl,Long.parseLong(userId));
        if(i == 0){
            return new Result(-1, "上传图片后台出错");
        }
        return new Result();
    }

    @Override
    public Result modifyAccountPwd(String newPwd,HttpSession session) {
        //数据校验
        if(newPwd == null || !(newPwd.matches("\\d{6}"))){
            return Result.DATA_FORMAT_ERROR;
        }
        //判断新密码与原始密码是否一样
        String userId = session.getAttribute(Constants.USER_SESSION_ID) + "";
        String oldPwd = accountMapper.selectPwdById(userId);
        newPwd = MD5Util.finalMD5(newPwd);
        if(newPwd.equals(oldPwd)){
            return new Result(-1, "新密码不能和旧密码一样");
        }
        //修改密码
        int i = accountMapper.updateAccountPwd(newPwd,userId);
        if(i == 0){
            return new Result(-1, "修改密码失败");
        }
        /*修改密码成功，需要执行重新登录，在重新登录之前，先把之前保存在USER_SESSION_KEY中的旧的登录信息给删除*/
        session.removeAttribute(Constants.USER_SESSION_KEY);
        return new Result();
    }

}


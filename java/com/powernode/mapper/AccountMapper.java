package com.powernode.mapper;

import com.powernode.domain.Account;
import com.powernode.dto.AccountDto;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AccountMapper {

    //登录
    Account selectByLogin(AccountDto accountDto);

    //判断登录的账号是否存在
    @Select("select count(*) from account where username=#{param1}")
    int selectUsernameIsExist(String username);

    //分页查询
    @Select("select id,username,pwd,img_url as imgUrl,create_time as createTime,update_time as updateTime," +
            "role,status from account")
    List<Account> selectAccountsByPage();

    //添加账号：只有超级管理员才拥有此权限
    int insert(String username);

    /*删除指定id的账号信息*/
    int deleteByPrimaryKey(Long id);

    /*重置密码：只有超级管理员才能重置密码（可以重置自己与其他人）*/
    @Update("update account set pwd='c2f365c379ea6da2c2e42675fae561ac',update_time=now() where id = #{param1}")
    int updateAccount(Long id);

    /*修改账号的使用状态*/
    @Update("update account set status = #{arg0} where id = #{arg1}")
    int updateAccountStatus(Integer status,Long id);

    /*上传头像*/
    @Update("update account set img_url = #{param1} where id = #{param2}")
    int updateAccountImgUrl(String imgPath,Long id);

    /*查询指定id的密码*/
    @Select("select pwd from account where id = #{arg0}")
    String selectPwdById(String id);

    /*修改密码*/
    @Update("update account set pwd = #{arg0} where id = #{arg1}")
    int updateAccountPwd(String newPwd,String id);

}

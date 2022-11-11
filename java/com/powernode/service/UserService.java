package com.powernode.service;

import com.powernode.common.Result;
import com.powernode.dto.UserDto;
import com.powernode.dto.UserSearchDto;

import javax.servlet.http.HttpSession;

public interface UserService {

    /*分页查询*/
    Result findUsersByPage(Integer pageNum,Integer pageSize);

    /*添加用户信息*/
    Result saveUser(UserDto userDto);

    //批量删除
    Result removeManyUser(String id);

    /*搜索*/
    Result findUsersBySearch(UserSearchDto userSearchDto);

    /*修改*/
    Result modifyUser(UserDto userDto);

    /*单个删除*/
    Result removeOneUser(Long id);

}

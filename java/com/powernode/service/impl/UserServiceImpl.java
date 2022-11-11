package com.powernode.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.powernode.common.Result;
import com.powernode.domain.User;
import com.powernode.dto.UserDto;
import com.powernode.dto.UserSearchDto;
import com.powernode.mapper.UserMapper;
import com.powernode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public Result findUsersByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userMapper.selectUsersByPage();
        PageInfo<User> pageInfo = new PageInfo<>(userList);

        Result result = new Result();
        result.setTotal(pageInfo.getTotal());
        result.setData(pageInfo.getList());
        return result;
    }

    @Override
    public Result saveUser(UserDto userDto) {
        //查询部门是否存在
        int i = userMapper.selectDeptIsExist(userDto);
        if(i == 0){
            return new Result(-1,"部门不存在");
        }
        /*查询指定记录是否已经存在*/
        int j = userMapper.selectUserIsExist(userDto);
        if(j == 1){
            return new Result(-1, "记录已经存在，不能重复添加");
        }
        //添加新的记录
        int n = userMapper.insert(userDto);
        if(n == 0){
            return new Result(-1, "添加失败");
        }
        return new Result();
    }

    @Override
    public Result removeManyUser(String id) {
        /*[1-9][0-9]*匹配一个正整数，[1-9]设置第一个数字不是0，[0-9]*表示任意多个数字 */
        /*+重复一次或者更多次*/
        if(id == null || !((id+",").matches("([1-9][0-9]*,)+"))){
            return Result.DATA_FORMAT_ERROR;
        }
        int i = userMapper.deleteManyUser(id);
        if(i == 0){
            return new Result(-1, "删除失败");
        }
        return new Result();
    }

    @Override
    public Result findUsersBySearch(UserSearchDto userSearchDto) {
        PageHelper.startPage(userSearchDto.getPageNum(),userSearchDto.getPageSize());
        List<User> userList = userMapper.selectUsersBySearch(userSearchDto);
        PageInfo<User> pageInfo = new PageInfo<>(userList);

        Result result = new Result();
        result.setTotal(pageInfo.getTotal());
        result.setData(pageInfo.getList());
        return result;
    }

    @Override
    public Result modifyUser(UserDto userDto) {
        //查询部门是否为空
        int i = userMapper.selectDeptIsExist(userDto);
        if(i == 0){
            return new Result(-1,"部门不存在");
        }
        //查询所修改的指定的记录是否已经存在
        int j = userMapper.selectUserIsExist(userDto);
        if(j == 1){
            return new Result(-1, "记录已经存在，请重新填写修改信息");
        }
        //修改数据
        int n = userMapper.updateUser(userDto);
        if(n == 0){
            return new Result(-1, "修改失败");
        }
        return new Result();
    }

    @Override
    public Result removeOneUser(Long id) {
        if(id <= 0){
            return Result.DATA_FORMAT_ERROR;
        }
        int i = userMapper.deleteOneUser(id);
        if(i == 0){
            return new Result(-1, "删除失败");
        }
        return new Result();
    }

}

package com.powernode.mapper;

import com.powernode.domain.User;
import com.powernode.dto.UserDto;
import com.powernode.dto.UserSearchDto;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {

    /*分页查询*/
    @Select("select u.*,d.name,d.loc from user u inner join dept d on u.dept_id = d.id")
    List<User> selectUsersByPage();

    /*查询部门是否已经存在*/
    @Select("select count(*) from dept where id=#{deptId}")
    int selectDeptIsExist(UserDto userDto);

    //添加客户信息
    int insert(UserDto userDto);

    //查询用户记录是否已经存在，避免重复添加
    @Select("select count(*) from user where username=#{username} and birthday=#{birthday} and " +
            "address=#{address} and sex=#{sex} and tel=#{tel}")
    int selectUserIsExist(UserDto userDto);

    //批量删除
    @Delete("delete from user where id in(${arg0})")
    int deleteManyUser(String id);

    /*检索*/
    List<User> selectUsersBySearch(UserSearchDto userSearchDto);

    /*修改*/
    int updateUser(UserDto userDto);

    /*单个删除*/
    @Delete("delete from user where id = #{arg0}")
    int deleteOneUser(Long id);

}

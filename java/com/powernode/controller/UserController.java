package com.powernode.controller;

import com.powernode.common.Result;
import com.powernode.dto.UserDto;
import com.powernode.dto.UserSearchDto;
import com.powernode.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/getUsersByPage.do")
    public Result getUsersByPage(
            Integer pageNum,
            @RequestParam(required = false,defaultValue = "10") Integer pageSize
    ){
        return userService.findUsersByPage(pageNum,pageSize);
    }

    @RequestMapping("/addUser.do")
    public Result addUser(@Valid UserDto userDto, BindingResult br){
        if(br.hasErrors()){
            return Result.DATA_FORMAT_ERROR;
        }
        return userService.saveUser(userDto);
    }

    @RequestMapping("/cutManyUser.do")
    public Result cutManyUser(String id){
        return userService.removeManyUser(id);
    }

    @RequestMapping("/getUsersBySearch.do")
    public Result getUsersBySearch(@Valid UserSearchDto userSearchDto,BindingResult br){
        if(br.hasErrors()){
            return Result.DATA_FORMAT_ERROR;
        }
        return userService.findUsersBySearch(userSearchDto);
    }

    @RequestMapping("/editUser.do")
    public Result editUser(@Valid UserDto userDto,BindingResult br){
        if(br.hasErrors()){
            return Result.DATA_FORMAT_ERROR;
        }
        return userService.modifyUser(userDto);
    }

    @RequestMapping("/cutOneUser.do")
    public Result cutOneUser(Long id){
        return userService.removeOneUser(id);
    }

}

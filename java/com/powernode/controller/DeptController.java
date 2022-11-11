package com.powernode.controller;

import com.powernode.common.Result;
import com.powernode.dto.DeptDto;
import com.powernode.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private DeptService deptService;

    /*分页*/
    @RequestMapping("/getDeptsByPage.do")
    public Result getDeptsByPage(
        @RequestParam(required = false,defaultValue = "1") Integer pageNum,
        @RequestParam(required = false,defaultValue = "10") Integer pageSize
    ){
        return deptService.findDeptsByPage(pageNum,pageSize);
    }

    @RequestMapping("/addDept.do")
    public Result addDept(@Valid DeptDto deptDto, BindingResult br){
        if(br.hasErrors()){
            return Result.DATA_FORMAT_ERROR;
        }
        return deptService.saveDept(deptDto);
    }

}

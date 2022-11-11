package com.powernode.service;

import com.powernode.common.Result;
import com.powernode.dto.DeptDto;

public interface DeptService {

    /*分页查询*/
    Result findDeptsByPage(Integer pageNum,Integer pageSize);

    /*添加部门信息*/
    Result saveDept(DeptDto deptDto);

}

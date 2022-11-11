package com.powernode.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.powernode.common.Result;
import com.powernode.domain.Dept;
import com.powernode.dto.DeptDto;
import com.powernode.mapper.DeptMapper;
import com.powernode.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public Result findDeptsByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Dept> deptList = deptMapper.selectDeptsByPage();
        PageInfo<Dept> pageInfo = new PageInfo<>(deptList);

        Result result = new Result();
        result.setData(pageInfo.getList());
        result.setTotal(pageInfo.getTotal());
        return result;
    }

    @Override
    public Result saveDept(DeptDto deptDto) {
        /*判断要添加的部门名称是否已经存在了*/
        int i = deptMapper.selectDeptNameIsExist(deptDto.getName());
        if(i == 1){
            return new Result(-1,"部门已经存在，不能重复添加");
        }
        int j = deptMapper.insertDept(deptDto);
        if(j == 0){
            return new Result(-1, "后台服务出错");
        }
        return new Result();
    }

}

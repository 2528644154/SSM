package com.powernode.dto;

import lombok.Data;

@Data
public class UserSearchDto  extends BaseDto{

    private String username;
    private String tel;
    private String deptName;
    private String sex;

}

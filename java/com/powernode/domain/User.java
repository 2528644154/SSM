package com.powernode.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String sex;
    private String tel;
    private Double sal;
    private String profession;
    private String address;
    private String remark;
    /*将部门表中的字段，也放进User类中*/
    private String name;
    private String loc;
    private Long deptId;

}

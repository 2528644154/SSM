package com.powernode.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Account {

    private Long id;
    private String username;
    private String pwd;
    private String imgUrl;
    /*@JsonFormat后台到前端时间格式保持一致*/
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createTime;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date updateTime;
    private Integer role;
    /*状态：1启用  0禁用*/
    private Integer status;

}

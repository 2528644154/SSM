package com.powernode.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;

    @NotNull(message="姓名格式错误")
    @Pattern(regexp = "[\\u4e00-\\u9fa5]{2,10}",message = "姓名格式错误")
    private String username;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;

    @NotNull(message = "性别格式错误")
    @Pattern(regexp = "[10]",message = "性别格式错误")
    private String sex;

    @NotNull(message = "手机号格式错误")
    @Pattern(regexp = "[1][3579]\\d{9}",message = "手机号格式错误")
    private String tel;

    @Min(value = 0L,message = "薪资格式错误")
    private Double sal;

    @NotNull(message = "职业格式错误")
    @Pattern(regexp = "[123]",message = "职业格式错误")
    private String profession;

    @NotNull(message = "地址格式错误")
    @Pattern(regexp = ".{10,200}",message = "地址格式错误")
    private String address;

    private String remark;

    private Long deptId;

}

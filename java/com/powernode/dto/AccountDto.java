package com.powernode.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/*dto:就是把需要传递的参数封装为一个class的形式。在开发中比较常用----web参数的传递*/
/*使用一个dto类型的对象，在java服务端可用dto类型对象直接接收*/
@Data
public class AccountDto {

    @NotNull
    @Pattern(regexp = "\\w{2,20}")
    private String username;
    @NotNull
    @Pattern(regexp = "\\d{6}")
    private String pwd;
    @NotNull
    @Pattern(regexp = "[0-9a-zA-Z]{4}")
    private String captcha;

}

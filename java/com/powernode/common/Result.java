package com.powernode.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Result {

    //状态码:200 0 --- 请求成功  -1 --- 请求失败
    private int code = 200;

    //分页查询时满足条件的总记录数
    private long total = 0;

    //当code = -1时，返回失败的原因
    private String msg;

    //存放分页的数据
    private Object data;

    //请求参数的格式错误
    public static final Result DATA_FORMAT_ERROR = new Result(-1,"请求参数格式错误");

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}

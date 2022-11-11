package com.powernode.dto;

import lombok.Data;

/*用于搜索功能的分页*/
@Data
public class BaseDto {

    //页码
    private Integer pageNum = 1;
    //条数
    private Integer pageSize = 10;

}

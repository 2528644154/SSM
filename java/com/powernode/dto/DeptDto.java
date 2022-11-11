package com.powernode.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class DeptDto {

    private Long id;

    @NotNull
    @Pattern(regexp = ".{4,20}")
    private String name;

    @NotNull
    @Pattern(regexp = ".{4,20}")
    private String loc;

}

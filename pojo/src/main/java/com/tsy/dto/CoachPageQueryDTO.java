package com.tsy.dto;

import lombok.Data;

@Data
public class CoachPageQueryDTO {
    private Integer page;
    private Integer pageSize;
    private String keyword; // 支持姓名、手机号、邮箱模糊搜索
}

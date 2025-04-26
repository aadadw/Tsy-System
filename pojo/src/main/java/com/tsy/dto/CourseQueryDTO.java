package com.tsy.dto;

import lombok.Data;

@Data
public class CourseQueryDTO {
    private String name;
    private String coach;
    private String level;
    private String category;
    private Integer page = 1;
    private Integer pageSize = 10;
    private Long userId; // 当前登录用户 ID，用于排除已报名课程
}
package com.tsy.vo;

import lombok.Data;

@Data
public class CourseVO {
    private Long id;
    private String name;
    private String description;
    private Integer durationMinutes;
    private String difficulty;
    private String category;
    private String tags;
    private String coverUrl;
    private Integer maxEnrollment;
    private String createTime;
    private Integer enrolledCount;//目前已报名人数
    private String coach; // 教练用户名
}

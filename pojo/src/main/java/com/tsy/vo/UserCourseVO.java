package com.tsy.vo;

import lombok.Data;

@Data
public class UserCourseVO {
    private Long id; // 课程ID
    private String name; // 课程名称
    private String description; // 课程描述
    private Integer durationMinutes; // 课程时长（分钟）
    private String difficulty; // 课程难度（如：入门、中级、高级）
    private String category; // 课程分类（如：力量训练、有氧运动等）
    private String tags; // 课程标签（如：减脂、增肌等）
    private String coverUrl; // 课程封面图片URL
    private String coach; // 教练姓名
    private String status; // 报名状态（如：报名中、报名成功、取消）
}

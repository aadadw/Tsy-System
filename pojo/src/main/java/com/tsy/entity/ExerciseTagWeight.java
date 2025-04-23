package com.tsy.entity;

import lombok.Data;

@Data
public class ExerciseTagWeight {
    private Long id;
    private String projectName;

    private Double muscle;       // 肌肉增长
    private Double shape;        // 塑形
    private Double core;         // 核心力量
    private Double metabolism;   // 基础代谢提升
    private Double fatBurn;      // 脂肪燃烧
    private Double cardio;       // 心肺功能
    private Double flexibility;  // 柔韧性
}

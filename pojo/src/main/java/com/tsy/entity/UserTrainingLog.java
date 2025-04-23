package com.tsy.entity;


import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserTrainingLog {

    private Long id;                 // 主键 ID

    private Long userId;             // 用户 ID

    private String projectName;      // 训练项目名称

    private Integer durationMinutes; // 持续时间（分钟）

    private Integer calories;        // 消耗的卡路里

    private LocalDate checkinDate;   // 打卡日期

    private LocalDateTime createdAt; // 创建时间
}
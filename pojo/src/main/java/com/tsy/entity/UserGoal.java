package com.tsy.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserGoal {
    private Long id;
    private Long userId;
    private String goalType;
    private String goalDesc;
    private LocalDate startDate;
    private Integer totalDays;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
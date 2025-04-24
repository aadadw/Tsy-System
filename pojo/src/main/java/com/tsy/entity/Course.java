package com.tsy.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Course {
    private Long id;
    private Long coachId;
    private String name;
    private String description;
    private Integer durationMinutes;
    private String difficulty;
    private String category;
    private String tags;
    private String coverUrl;
    private Integer maxEnrollment;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

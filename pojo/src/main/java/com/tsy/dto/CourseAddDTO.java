package com.tsy.dto;

import lombok.Data;

@Data
public class CourseAddDTO {
    private String name;
    private String description;
    private Integer durationMinutes;
    private String difficulty;
    private String category;
    private String tags;
    private String coverUrl;
    private Integer maxEnrollment;

}
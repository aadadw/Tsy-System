package com.tsy.vo;

import lombok.Data;

@Data
public class CoachQualificationVO {
    private Long userId;
    private String username;
    private String phone;
    private String avatarUrl;
    private String certificationUrl;
    private String speciality;
    private Integer yearsExperience;
    private String bio;
}

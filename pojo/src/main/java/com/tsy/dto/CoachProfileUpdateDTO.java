package com.tsy.dto;

import lombok.Data;

@Data
public class CoachProfileUpdateDTO {
    private Long userId;
    private String bio;
    private String certificationUrl;
    private String speciality;
    private Integer yearsExperience;
    private String contactWechat;
}

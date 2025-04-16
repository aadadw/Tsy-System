package com.tsy.entity;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CoachInfo {
    private Long userId;
    private String avatarUrl;
    private String bio;
    private String certificationUrl;
    private Integer isVerified;
    private String speciality;
    private Integer yearsExperience;
    private String contactWechat;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

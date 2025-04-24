package com.tsy.vo;

import lombok.Data;

@Data
public class CoachProfileVO {
    private Long userId;
    private String username;
    private String avatarUrl;
    private String bio;
    private String certificationUrl;
    private Integer isVerified;
    private String speciality;
    private Integer yearsExperience;
    private String contactWechat;
    private String rejectReason;
}

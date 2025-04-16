package com.tsy.dto;

import lombok.Data;

@Data
public class CoachRegisterDTO {
    private String username;
    private String name;
    private String password;
    private String email;
    private String role;
    private String avatarUrl;
    private Integer age;
    private String gender;
    private String bio;
    private String certificationUrl;
    private Integer isVerified; // 0未认证 1已认证 2待审核 3驳回
    private String speciality;
    private Integer yearsExperience;
    private String contactWechat;
    private String videoIntroUrl;
}

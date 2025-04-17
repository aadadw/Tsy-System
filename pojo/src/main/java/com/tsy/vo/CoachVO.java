package com.tsy.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CoachVO {
    private Long id;
    private String username;
    private String phone;
    private String email;
    private Integer status;
    private LocalDateTime createTime;

    private String avatarUrl;
    private String bio;
    private String certificationUrl;
    private Integer isVerified; // 审核状态：0未认证，1通过，2待审核，3驳回
    private String speciality;
    private Integer yearsExperience;
    private String contactWechat;
}

package com.tsy.entity;


import lombok.Data;

@Data
public class CoachInfo {
    private Long userId; // 对应 user_base.id
    private String avatarUrl;
    private String bio;
    private String certificationUrl; // 教练资格证路径
    private Integer isVerified; // 0-未认证，1-已认证
}
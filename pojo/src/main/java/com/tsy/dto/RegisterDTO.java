package com.tsy.dto;

import lombok.Data;

@Data
public class RegisterDTO {
    private String username;
    private String name;
    private String password;
    private String email;
    private String role;
    private String avatarUrl;
    private Integer age;
    private String gender;
    private String bio;
    private String phone;
    // 普通用户扩展字段
    private String goal;
    private Double height;
    private Double weight;
    private Integer level;
    // 教练扩展字段
    private String certificationUrl;
    private Integer isVerified;//0未认证 1已认证 2待审核 3驳回
    private String speciality;//擅长方向（如增肌、减脂、产后恢复等）
    private Integer yearsExperience;
    private String contactWechat;//联系方式
}
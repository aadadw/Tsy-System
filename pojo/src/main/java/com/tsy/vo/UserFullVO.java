package com.tsy.vo;

import lombok.Data;



import lombok.Data;

import java.time.LocalDateTime;

@Data
//user_info+user_base表
public class UserFullVO {

    // === user_base 字段 ===
    private Long id;               // 用户ID
    private String username;       // 用户名
    private String email;          // 邮箱
    private String phone;          // 手机号
    private String role;           // 用户角色
    private Integer status;        // 启用状态（0正常 1禁用）
    private LocalDateTime createTime; // 注册时间

    // === user_info 字段 ===
    private String avatarUrl;      // 头像地址
    private String gender;         // 性别
    private Integer age;           // 年龄
    private Double height;         // 身高(cm)
    private Double weight;         // 体重(kg)
    private String bio;            // 个性签名 / 简介
    private String goal;           // 健身目标
    private Integer level;         // 健身等级（1初级 2中级 3高级）
}



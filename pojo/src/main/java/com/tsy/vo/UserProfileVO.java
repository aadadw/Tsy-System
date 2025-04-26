package com.tsy.vo;

import lombok.Data;

@Data
public class UserProfileVO {
    private String username;     // 用户昵称（user_base.username）
    private String email;        // 邮箱（user_base.email）
    private String phone;        // 手机号（user_base.phone）
    private Integer age;         // 年龄（user_base.age）
    private String avatarUrl;    // 头像 URL（user_base.avatar_url）

    private String gender;       // 性别（user_info.gender）
    private Double height;       // 身高（user_info.height）
    private Double weight;       // 体重（user_info.weight）
    private String goal;         // 健身目标（user_info.goal）
    private String bio;          // 个人简介（user_info.bio）
}
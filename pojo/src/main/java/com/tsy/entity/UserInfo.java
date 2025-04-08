package com.tsy.entity;

import lombok.Data;

@Data
public class UserInfo {
    private Long userId; // 对应 user_base.id
    private String avatarUrl;
    private String gender;
    private Integer age;
    private Double height;
    private Double weight;
    private String bio;
}

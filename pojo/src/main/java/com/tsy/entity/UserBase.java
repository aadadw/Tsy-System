package com.tsy.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserBase {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private Integer age;
    private String role; // admin, coach, user
    private Integer status; // 0-正常，1-禁用
    private LocalDateTime createTime;
}

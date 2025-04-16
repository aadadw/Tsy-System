package com.tsy.dto;

import lombok.Data;

@Data
public class UserRegisterDTO {
    private String username;
    private String password;
    private String email;
    private String role;
    private String avatarUrl;
    private Integer age;
    private String gender;
    private String bio;
    private String goal;
    private Double height;
    private Double weight;
    private Integer level;
}

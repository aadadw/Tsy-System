package com.tsy.dto;

import lombok.Data;

@Data
public class AdminUserUpdateDTO {
    private Long id;
    private String username;
    private String phone;
    private String email;
    private Integer age;
    private Integer status;
}

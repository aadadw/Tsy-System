package com.tsy.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserPageQueryDTO implements Serializable {
    //页码
    private int page;
    //每页显示记录数
    private int pageSize;
    private String keyword; // 支持用户名 / 手机号 / 邮箱模糊查询
}

package com.tsy.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Announcement {
    private Long id;
    private String title;
    private String category; // 广告 / 系统通知
    private String content;
    private String imageUrl;
    private Integer isEnabled;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
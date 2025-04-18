package com.tsy.dto;

import lombok.Data;

@Data
public class AnnouncementCreateDTO {
    private String title;
    private String category;      // 广告 / 系统通知
    private String content;
    private String imageUrl;
    private Integer isEnabled;    // 1 启用，0 禁用
}
package com.tsy.dto;

import lombok.Data;

@Data
public class AnnouncementPageQueryDTO {
    private Integer page;
    private Integer pageSize;
    private String keyword;     // 公告标题关键词
    private String category;    // 公告分类（广告/系统通知）
}
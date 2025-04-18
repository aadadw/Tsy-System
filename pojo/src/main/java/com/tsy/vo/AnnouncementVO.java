package com.tsy.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AnnouncementVO {
    private Long id;
    private String title;
    private String category;
    private String content;
    private String imageUrl;
    private Integer isEnabled;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
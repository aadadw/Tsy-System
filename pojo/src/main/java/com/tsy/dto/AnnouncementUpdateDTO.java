package com.tsy.dto;

import lombok.Data;

@Data
public class AnnouncementUpdateDTO {
    private Long id;
    private String title;
    private String category;
    private String content;
    private String imageUrl;
    private Integer isEnabled;
}
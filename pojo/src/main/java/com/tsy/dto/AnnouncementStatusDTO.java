package com.tsy.dto;

import lombok.Data;

@Data
public class AnnouncementStatusDTO {
    private Long id;           // 公告 ID
    private Integer isEnabled; // 0 禁用，1 启用
}

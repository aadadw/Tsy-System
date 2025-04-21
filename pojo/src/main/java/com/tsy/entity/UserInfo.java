package com.tsy.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserInfo {
    private Long userId; // 对应 user_base.id
//    private String avatarUrl;
    private String gender;
    private Double height;
    private Double weight;
    private String bio;
    private String goal;//健身目标（如“增肌”、“减脂”、“塑形”）
    private Integer level;//健身等级（初级 1、中级 2、高级 3）
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}

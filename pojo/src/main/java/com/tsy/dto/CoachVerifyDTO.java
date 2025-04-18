package com.tsy.dto;

import lombok.Data;

@Data
public class CoachVerifyDTO {
    private Long userId;
    private Integer isVerified; // 1 通过，3 驳回
}

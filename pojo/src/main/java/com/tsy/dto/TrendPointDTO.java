package com.tsy.dto;

import lombok.Data;

@Data
public class TrendPointDTO {
    private String date;//yyyy-MM-dd
    private Integer count;//记录当日打卡情况
}

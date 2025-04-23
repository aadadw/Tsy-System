package com.tsy.vo;

import lombok.Data;

@Data
public class DailyCalorieVO {
    private String date;     // yyyy-MM-dd
    private Integer calories;
}
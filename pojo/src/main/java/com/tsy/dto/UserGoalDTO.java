package com.tsy.dto;

import lombok.Data;

@Data
public class UserGoalDTO {
    private String goalType;
    private String goalDesc;
    private Integer totalDays;
}

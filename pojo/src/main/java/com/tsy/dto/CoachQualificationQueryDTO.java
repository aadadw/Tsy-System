package com.tsy.dto;

import lombok.Data;

@Data
public class CoachQualificationQueryDTO {
    private Integer page;
    private Integer pageSize;
    private String keyword;
}
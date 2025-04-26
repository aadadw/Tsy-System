package com.tsy.dto;

import lombok.Data;

@Data
public class PageQueryDTO {
    private Integer page = 1;
    private Integer pageSize = 10;
}

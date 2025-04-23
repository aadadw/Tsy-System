package com.tsy.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectProportionVO {
    private String name;   // 项目名称
    private Integer value; // 总时长（分钟）
}

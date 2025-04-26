package com.tsy.vo;

import lombok.Data;

@Data
public class ReservationVO {
    private Long id; // 预约ID
    private String studentName; // 学员姓名
    private String courseName; // 课程名称
    private String enrollTime; // 预约日期
    private String status; // 预约状态（待确认、已确认、已取消）
}

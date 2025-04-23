package com.tsy.vo;

import lombok.Data;

import java.time.LocalDate;

//以下数据都应该是从目标起始那一天开始统计的
@Data
public class UserGoalVO {
    private String goalType;//目标类型：
    private String goalDesc;//目标描述：
    private Integer checkinCount;//已打卡次数：
    private Integer totalDuration; // 累计训练时长
    private Integer progress;//目标进度（签到天数/目标总天数）：返回number前端换算成百分比显示
    private Integer totalCalories;//从目标开始时计算
    private Integer daysThisWeek;//从目标开始时计算本周打卡天数
    private LocalDate lastCheckin;//最近打卡哪一天
    private String inspire;//鼓励语，找个库存一下？
    private LocalDate StartData;//目标起始时间
}

package com.tsy.dto;

import lombok.Data;
//下面数据均是目标时段内
@Data
public class UserGoalStatsDTO {
    private Integer checkinCount;//总打卡次数（count *）
    private Integer totalDuration;//总训练时长（sum(duration_minutes)）
    private Integer totalCalories;//总卡路里（sum(calories)）
    private Integer progressDays;//有打卡的天数（count(distinct checkin_date)）


}

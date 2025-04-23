package com.tsy.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class TrendDataVO {
    @JsonProperty("xAxis")
    private List<String> xAxis; // 日期（如 04-15, 04-16）
    private List<Integer> data; // 每日打卡次数或卡路里
}

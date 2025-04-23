package com.tsy.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserPunchDTO {
    private Long userId;
    private List<ExerciseItem> exercises;

    @Data
    public static class ExerciseItem {
        private String projectName;
        private Integer durationMinutes;
    }
}

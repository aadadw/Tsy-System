package com.tsy.model;

import com.tsy.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CourseWithScore {
    private Course course;
    private double score;
}

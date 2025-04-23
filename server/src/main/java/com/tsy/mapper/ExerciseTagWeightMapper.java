package com.tsy.mapper;

import com.tsy.entity.ExerciseTagWeight;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ExerciseTagWeightMapper {
    List<ExerciseTagWeight> selectAll();
}

package com.tsy.mapper;

import com.tsy.entity.ExerciseTagWeight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExerciseTagWeightMapper {
    // 查询所有完整权重数据（启动时缓存用）
    List<ExerciseTagWeight> selectAll();
    // 查询所有训练动作
    @Select("SELECT project_name FROM exercise_tag_weight")
    List<String> selectAllExerciseNames();
}

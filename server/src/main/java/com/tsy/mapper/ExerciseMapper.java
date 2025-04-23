package com.tsy.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;

@Mapper
public interface ExerciseMapper {
    /**
     * 获取met
     * @param projectName
     * @return
     */
    @Select("select mets from exercise_reference where name = #{projectName}")
    BigDecimal getMetsByName(String projectName);
}

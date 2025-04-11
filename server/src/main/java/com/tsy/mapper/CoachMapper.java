package com.tsy.mapper;

import com.tsy.annotation.AutoFill;
import com.tsy.entity.CoachInfo;
import com.tsy.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CoachMapper {
    /**
     * 写入教练数据
     * @param coachInfo
     */
    @AutoFill(OperationType.INSERT)//TODO
    void insert(CoachInfo coachInfo);
}

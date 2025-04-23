package com.tsy.mapper;

import com.tsy.annotation.AutoFill;
import com.tsy.entity.UserGoal;
import com.tsy.enumeration.OperationType;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserGoalMapper {
    /**
     * 插入时段目标数据
     * @param goal
     */
    @AutoFill(OperationType.INSERT)
    void insert(UserGoal goal);

    /**
     * 查询用户时段目标
     * @param userId
     * @return
     */
    @Select("select * from  user_goal where user_id=#{userId}")
    UserGoal getByUserId(Long userId);
}

package com.tsy.mapper;

import com.tsy.annotation.AutoFill;
import com.tsy.dto.UserPageQueryDTO;
import com.tsy.entity.UserInfo;
import com.tsy.enumeration.OperationType;
import com.tsy.vo.UserFullVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDate;
import java.util.List;

@Mapper
public interface UserMapper {
    /**
     * 写入普通用户数据
     * @param userInfo
     */
    @AutoFill(OperationType.INSERT)
    void insert(UserInfo userInfo);

    /**
     * 外键连接userbase和userinfo，查询应该在管理页面展示的数据
     * @param dto
     * @return
     */

    List<UserFullVO> pageQuery(UserPageQueryDTO dto);

    /**
     * 根据userid删除userinfo和userbase的数据
     * @param userId
     */
    @Delete("delete from user_info where user_id=#{userId}")
    void deleteByUserId(Long userId);

    /**
     * 修改状态
     * @param id
     * @param status
     */

    void updateStatus(Long id, Integer status);

    /**
     * 逻辑删除普通用户
     * @param id
     */
    void markAsDeleted(Long id);

    /**
     * 通过userid查询体重
     * @param userId
     * @return
     */
    @Select("select  weight from user_info where user_id=#{userId}")
    Integer getWeightByUserId(Long userId);

    /**
     * 通过当日日期和userId查询
     * @param userId
     * @param today
     * @return
     */
    int countTodayPunch(Long userId, LocalDate today);
}

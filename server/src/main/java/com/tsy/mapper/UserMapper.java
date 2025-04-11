package com.tsy.mapper;

import com.tsy.annotation.AutoFill;
import com.tsy.dto.UserPageQueryDTO;
import com.tsy.entity.UserInfo;
import com.tsy.enumeration.OperationType;
import com.tsy.vo.UserFullVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

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
}

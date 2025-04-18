package com.tsy.mapper;

import com.tsy.dto.AnnouncementPageQueryDTO;
import com.tsy.dto.AnnouncementStatusDTO;
import com.tsy.dto.AnnouncementUpdateDTO;
import com.tsy.entity.Announcement;
import com.tsy.vo.AnnouncementVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnnouncementMapper {
    /**
     * 新增新闻
     * @param announcement
     */
    void insert(Announcement announcement);

    /**
     * 分页查询
     * @param dto
     * @return
     */
    List<AnnouncementVO> pageQuery(AnnouncementPageQueryDTO dto);

    /**
     * 修改状态
     * @param dto
     */
    void updateStatus(AnnouncementStatusDTO dto);

    /**
     * 修改通知
     * @param dto
     */
    void update(AnnouncementUpdateDTO dto);
}

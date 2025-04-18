package com.tsy.service;

import com.tsy.dto.AnnouncementCreateDTO;
import com.tsy.dto.AnnouncementPageQueryDTO;
import com.tsy.dto.AnnouncementStatusDTO;
import com.tsy.dto.AnnouncementUpdateDTO;
import com.tsy.result.PageResult;

public interface AnnouncementService {
    void createAnnouncement(AnnouncementCreateDTO dto);

    /**
     * 显示通知数据
     * @param dto
     * @return
     */
    PageResult pageQuery(AnnouncementPageQueryDTO dto);

    /**
     * 更改状态
     * @param dto
     */
    void updateStatus(AnnouncementStatusDTO dto);

    /**
     * 修改通知
     * @param dto
     */
    void update(AnnouncementUpdateDTO dto);
}

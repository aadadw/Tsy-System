package com.tsy.service.impl;

import com.tsy.dto.AnnouncementCreateDTO;
import com.tsy.dto.AnnouncementPageQueryDTO;
import com.tsy.dto.AnnouncementStatusDTO;
import com.tsy.dto.AnnouncementUpdateDTO;
import com.tsy.entity.Announcement;
import com.tsy.mapper.AnnouncementMapper;
import com.tsy.result.PageResult;
import com.tsy.service.AnnouncementService;
import com.tsy.vo.AnnouncementVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import java.util.List;

/**
 * 新增新闻
 */
@Service
public class AnnouncementServiceImpl implements AnnouncementService {
    @Autowired
    private AnnouncementMapper announcementMapper;
    /**
     * 新增新闻
     * @param dto
     */
    @Override
    public void createAnnouncement(AnnouncementCreateDTO dto) {
        Announcement ann = new Announcement();
        ann.setTitle(dto.getTitle());
        ann.setCategory(dto.getCategory());
        ann.setContent(dto.getContent());
        ann.setImageUrl(dto.getImageUrl());
        ann.setIsEnabled(dto.getIsEnabled());
        announcementMapper.insert(ann);
    }

    /**
     *  分页查询通知数据
     * @param dto
     * @return
     */
    @Override
    public PageResult pageQuery(AnnouncementPageQueryDTO dto) {
        PageHelper.startPage(dto.getPage(), dto.getPageSize());
        List<AnnouncementVO> list = announcementMapper.pageQuery(dto);
        Page<AnnouncementVO> page = (Page<AnnouncementVO>) list;
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 更改状态
     * @param dto
     */
    @Override
    public void updateStatus(AnnouncementStatusDTO dto) {
        announcementMapper.updateStatus(dto);
    }

    /**
     * 修改通知
     * @param dto
     */
    @Override
    public void update(AnnouncementUpdateDTO dto) {
        announcementMapper.update(dto);
    }
}

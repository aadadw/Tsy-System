package com.tsy.controller.admin;

import com.tsy.dto.AnnouncementCreateDTO;
import com.tsy.dto.AnnouncementPageQueryDTO;
import com.tsy.dto.AnnouncementStatusDTO;
import com.tsy.dto.AnnouncementUpdateDTO;
import com.tsy.result.PageResult;
import com.tsy.result.Result;
import com.tsy.service.AnnouncementService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Api(tags = "管理员公告操作相关接口")
@RestController
@RequestMapping("/api/admin/announcement")
public class AdminAnnouncementController {
    @Autowired
    private AnnouncementService announcementService;

    /**
     * 新增新闻
     * @param dto
     * @return
     */
    @PostMapping("/create")
    public Result create(@RequestBody AnnouncementCreateDTO dto) {
        log.info("插入广告/通知 数据");
        announcementService.createAnnouncement(dto);
        return Result.success();
    }

    /**
     * 显示通知数据
     * @param dto
     * @return
     */
    @GetMapping("/list")
    public Result<PageResult> list(AnnouncementPageQueryDTO dto) {
        PageResult pageResult = announcementService.pageQuery(dto);
        return Result.success(pageResult);
    }

    /**
     * 更改状态
     * @param dto
     * @return
     */
    @PutMapping("/status")
    public Result updateStatus(@RequestBody AnnouncementStatusDTO dto) {
        announcementService.updateStatus(dto);
        return Result.success();
    }

    /**
     * 修改通知
     * @param dto
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody AnnouncementUpdateDTO dto) {
        announcementService.update(dto);
        return Result.success();
    }

}

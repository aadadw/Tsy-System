package com.tsy.controller.admin;

import com.tsy.dto.AdminUserUpdateDTO;
import com.tsy.result.Result;
import com.tsy.service.AdminService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@Api(tags = "管理员相关接口")
@RestController
@RequestMapping("/api/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * 管理员修改用户资料
     * @param dto
     * @return
     */
    @PutMapping("/update")
    public Result updateUser(@RequestBody AdminUserUpdateDTO dto) {
        log.info("修改用户信息：{}",dto);
        adminService.updateUserWithRoleSwitch(dto);
        return Result.success("更新成功");
    }
}

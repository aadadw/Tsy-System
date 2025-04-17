package com.tsy.controller.admin;

import com.tsy.dto.AdminUserUpdateDTO;
import com.tsy.result.PageResult;
import com.tsy.result.Result;
import com.tsy.service.AdminService;
import com.tsy.service.UserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.tsy.dto.UserPageQueryDTO;
@Slf4j
@Api(tags = "管理员相关接口")
@RestController
@RequestMapping("/api/admin/")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @Autowired
    private UserService userService;

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

    @GetMapping("/list")
    public Result<PageResult> list(@RequestBody UserPageQueryDTO dto) {
        PageResult result = userService.pageQuery(dto);
        return Result.success(result);
    }
}

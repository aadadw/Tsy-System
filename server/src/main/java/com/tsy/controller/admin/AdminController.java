package com.tsy.controller.admin;

import com.tsy.dto.AdminUserUpdateDTO;
import com.tsy.dto.CoachPageQueryDTO;
import com.tsy.dto.UserStatusDTO;
import com.tsy.result.PageResult;
import com.tsy.result.Result;
import com.tsy.service.AdminService;
import com.tsy.service.CoachService;
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
    @Autowired
    private CoachService coachService;

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

    /**
     * 模糊查询用户
     * @param dto
     * @return
     */
    @GetMapping("/list")
    public Result<PageResult> listUser(@RequestBody UserPageQueryDTO dto) {
        PageResult result = userService.pageQuery(dto);
        return Result.success(result);
    }

    /**
     *获取coach数据
     * @param dto
     * @return
     */
    @GetMapping("/coach/list")
    public Result<PageResult> getCoachList(CoachPageQueryDTO dto) {
        PageResult result = coachService.pageQueryCoach(dto);
        return Result.success(result);
    }

    /**
     * 修改教练状态
     * @param dto
     * @return
     */
    @PutMapping("/coach/status")
    public Result updateCoachStatus(@RequestBody UserStatusDTO dto) {
        coachService.updateStatus(dto);
        return Result.success();
    }
    /**
     * 修改普通用户状态
     * @param dto
     * @return
     */
    @PutMapping("/user/status")
    public Result updateUserStatus(@RequestBody UserStatusDTO dto) {
        userService.updateStatus(dto);
        return Result.success();
    }
    /**
     * 删除教练
     * @param id
     * @return
     */
    @DeleteMapping("/coach/{id}")
    public Result deleteCoach(@PathVariable Long id) {
        log.info("逻辑删除：{}",id);
        coachService.deleteCoachLogic(id); // 可逻辑删除或物理删除
        return Result.success();
    }

    /**
     * 删除用户
     * @param id
     * @return
     */
    @DeleteMapping("/user/{id}")
    public Result deleteUser(@PathVariable Long id) {
        userService.logicDeleteById(id);
        return Result.success();
    }
}

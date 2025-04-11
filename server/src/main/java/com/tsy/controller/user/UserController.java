package com.tsy.controller.user;

import com.tsy.dto.UserPageQueryDTO;
import com.tsy.result.PageResult;
import com.tsy.result.Result;
import com.tsy.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 普通用户管理
 */
@Api(tags = "普通用户相关接口")
@Slf4j
@RestController
@RequestMapping("/api/user/")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * 用户分页查询
     * @param userPageQueryDTO
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("用户分页查询")
    public Result<PageResult> queryPage(UserPageQueryDTO userPageQueryDTO){
        log.info("用户分页查询，参数：{}",userPageQueryDTO);
        PageResult pageResult = userService.pageQuery(userPageQueryDTO);
        return  Result.success(pageResult);
    }

    /**
     * 根据userid删除普通用户数据
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    @ApiOperation("删除普通用户")
    public Result delete(@PathVariable Long userId){
        log.info("删除普通用户，userId：{}",userId);
        userService.deleteByUserId(userId);
        return Result.success();
    }
}

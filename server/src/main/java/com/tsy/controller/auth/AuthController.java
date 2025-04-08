package com.tsy.controller.auth;

import com.tsy.constant.JwtClaimsConstant;
import com.tsy.dto.LoginRequestDTO;
import com.tsy.entity.UserBase;
import com.tsy.propertise.JwtProperties;
import com.tsy.result.Result;
import com.tsy.service.AuthService;
import com.tsy.utils.JwtUtil;
import com.tsy.vo.LoginResponseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@Slf4j
@RestController
@RequestMapping("/api/auth")
@Api(tags = "用户登录")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private JwtProperties jwtProperties;
    /**
     * 用户登录
     *
     * @param loginRequestDTO
     * @return
     */
    @ApiOperation("登录")
    @PostMapping("/login")
    public Result<LoginResponseVO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        log.info("登录：{}", loginRequestDTO);
        UserBase userBase = authService.login(loginRequestDTO);

        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, userBase.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        LoginResponseVO loginResponseVO = LoginResponseVO.builder()
                .id(userBase.getId())
                .name(userBase.getName())
                .username(userBase.getUsername())
                .token(token)
                .build();

        return Result.success(loginResponseVO);
    }
}

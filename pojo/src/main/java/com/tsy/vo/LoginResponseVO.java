package com.tsy.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "登录返回的数据格式")
public class LoginResponseVO {
    @ApiModelProperty("主键值")
    private Long id;
//    @ApiModelProperty("性名")
//    private String name;
    @ApiModelProperty("邮箱")
    private  String email;
    @ApiModelProperty("电话")
    private String phone;
    @ApiModelProperty("用户名")
    private String username;
    @ApiModelProperty("角色")
    private String role;
    @ApiModelProperty("jwt令牌")
    private String token;
    @ApiModelProperty("头像链接")
    private String avatarUrl;

}

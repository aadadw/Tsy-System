package com.tsy.controller.admin;

import com.tsy.constant.MessageConstant;
import com.tsy.result.Result;
import com.tsy.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin/common")
@Api(tags = "通用接口")
@Slf4j
public class CommonController {
    @Autowired
    private AliOssUtil aliOssUtil;
    //文件上传
    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file){//参数名要和前端一致（接口文档
        try {
            String orginalFilename = file.getOriginalFilename();//获取原始文件名
            //获取原始文件后缀（如png
            String extension = orginalFilename.substring(orginalFilename.lastIndexOf("."));
            //构造新文件名字
            String objectName = UUID.randomUUID().toString()+ extension;
            //上传文件并获取访问路径
            String filePath = aliOssUtil.upload(file.getBytes(),objectName);
            return Result.success(filePath);//将图片访问路径返回前端
        } catch (IOException e) {
            log.error("文件上传失败：{}",e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
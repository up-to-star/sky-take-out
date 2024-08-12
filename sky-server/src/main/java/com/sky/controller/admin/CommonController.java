package com.sky.controller.admin;

import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
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
@RequestMapping("/admin/common")
@Slf4j
@Api(tags = "通用接口")
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            if (fileName != null) {
                fileName = fileName.substring(fileName.lastIndexOf("."));
            }
            String name = UUID.randomUUID() + fileName;
            String filePath = aliOssUtil.upload(file.getBytes(), name);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("文件上传失败");
        }
        return Result.error("文件上传失败");
    }
}

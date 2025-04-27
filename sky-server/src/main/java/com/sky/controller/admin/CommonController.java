package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

/**
 * 通用接口
 */
@RestController
@RequestMapping("/admin/common")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * 文件上传
     * @param file
     * @return
     *
     * 形参名与前端提交的参数名保持一致
     * 如果@RequestParam("file") MultipartFile xxx
     * 那xxx随意
     * @RequestParam("file")告诉 Spring 在处理请求时，需要从请求中查找名为 file 的参数，并将其值绑定到方法参数 MultipartFile file
     */
    @PostMapping("/upload")
    public Result<String> upload(MultipartFile file){
        log.info("文件上传:{}",file);

        try{
            // 原始文件名
            String originalFilename = file.getOriginalFilename();

            // 截取文件名后缀
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String objectName = UUID.randomUUID().toString() + suffix;
            log.info("objectName:{}",objectName);

            // 上传到OSS，返回文件请求路径
            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        }catch (Exception e){
            log.error("文件上传失败:{}",e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}

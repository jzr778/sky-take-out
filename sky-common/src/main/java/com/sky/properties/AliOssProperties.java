package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件中配置项阿里云文件上传的属性
 */
@Component
@ConfigurationProperties(prefix = "sky.alioss") //用于将外部配置文件中的属性绑定到Java对象中
@Data
public class AliOssProperties {

    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String bucketName;

}

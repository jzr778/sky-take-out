package com.sky.annotation;

import com.sky.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 用于表示某个方法需要进行功能字段自动填充处理
 */
@Target(ElementType.METHOD) // 指定注解只能加在方法上
@Retention(RetentionPolicy.RUNTIME) // 定义注解的生命周期，注解在运行时被保留，可以通过反射获取注解信息
public @interface AutoFill {
    // 数据库操作类型：UPDATE INSERT
    OperationType value();
}

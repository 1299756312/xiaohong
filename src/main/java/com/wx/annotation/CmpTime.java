package com.wx.annotation;

import java.lang.annotation.*;

@Target({ElementType.METHOD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
// 是否生成注解文档
@Documented
public @interface CmpTime {

}

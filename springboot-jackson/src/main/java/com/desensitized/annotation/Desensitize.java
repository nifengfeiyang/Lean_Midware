package com.desensitized.annotation;

import com.desensitized.serializer.ObjectDesensitizeSerializer;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.desensitized.desensitization.Desensitization;

import java.lang.annotation.*;


@Target({ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = ObjectDesensitizeSerializer.class)
@Documented
public @interface Desensitize {
    /**
     * 对象脱敏器实现
     */
    @SuppressWarnings("all")
    Class<? extends Desensitization<?>> desensitization();


}
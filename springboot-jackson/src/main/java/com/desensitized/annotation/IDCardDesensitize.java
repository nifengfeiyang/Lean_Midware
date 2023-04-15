package com.desensitized.annotation;

import com.desensitized.desensitization.IDCardDesensitization;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;

import java.lang.annotation.*;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Desensitize(desensitization = IDCardDesensitization.class)
@Documented
public @interface IDCardDesensitize {
}

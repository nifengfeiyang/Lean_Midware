package com.desensitized.annotation;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.desensitized.desensitization.EmailDesensitization;

import java.lang.annotation.*;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@Desensitize(desensitization = EmailDesensitization.class)
@Documented
public @interface EmailDesensitize {
}
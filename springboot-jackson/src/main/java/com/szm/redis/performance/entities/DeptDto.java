package com.szm.redis.performance.entities;

import com.desensitized.annotation.EmailDesensitize;
import com.desensitized.annotation.PhoneDesensitize;
import lombok.Data;

@Data
public class DeptDto {
    private String name;
    private String addr;
    @EmailDesensitize
    private String email;
    @PhoneDesensitize
    private String phone;
}

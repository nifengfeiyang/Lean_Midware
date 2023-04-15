package com.szm.redis.performance.entities;

import com.desensitized.annotation.PhoneDesensitize;
import lombok.Data;

@Data
public class UserDto {
    private String name;
    @PhoneDesensitize
    private String phone;
    private Integer age;
    private DeptDto deptDto;
}

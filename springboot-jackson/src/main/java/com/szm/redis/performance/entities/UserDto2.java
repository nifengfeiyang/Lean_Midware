package com.szm.redis.performance.entities;

import lombok.Data;

@Data
public class UserDto2 {
    private String name;
    private String phone;
    private Integer age;
    private DeptDto2 deptDto;
}

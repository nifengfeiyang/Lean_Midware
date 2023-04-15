package com.szm.redis.performance.control;

import com.szm.redis.performance.entities.*;
import com.szm.redis.performance.service.PaymentService;
import com.szm.redis.performance.vo.RequestVO;
import com.szm.redis.performance.vo.ResponseVO;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 支付信息控制器
 *
 * @author 沈子明
 * @date 2020/2/18 10:40
 **/
@RestController
@RequestMapping("/jackson")
public class RedisController {

    /**
     * 下标自增原子类实现
     */
    static AtomicInteger INDEX = new AtomicInteger(1);
    /**
     * 下标前缀，取值为：{@value}
     */
    private final static String PREFIX = "index_";


    @Resource
    private PaymentService paymentService;


    @RequestMapping("/testDesensitize")
    public UserDto testDesensitize(@RequestBody Payment payment){
        UserDto userDto = new UserDto();
        DeptDto deptDto = new DeptDto();
        userDto.setAge(30);
        userDto.setDeptDto(deptDto);
        userDto.setPhone("15811111466");
        userDto.setName("张三");

        deptDto.setEmail("shen_ziming@123.com");
        deptDto.setPhone("15822221466");
        deptDto.setAddr("陶然亭");
        return userDto;
    }

    @RequestMapping("/testDesensitize2")
    public UserDto2 testDesensitize2(@RequestBody Payment payment){
        UserDto2 userDto = new UserDto2();
        DeptDto2 deptDto = new DeptDto2();
        userDto.setAge(30);
        userDto.setDeptDto(deptDto);
        userDto.setPhone("15811111466");
        userDto.setName("张三");

        deptDto.setEmail("shen_ziming@123.com");
        deptDto.setPhone("15822221466");
        deptDto.setAddr("陶然亭");
        return userDto;
    }

}

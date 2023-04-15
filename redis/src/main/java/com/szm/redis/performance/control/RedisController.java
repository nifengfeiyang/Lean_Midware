package com.szm.redis.performance.control;

import com.szm.redis.performance.entities.Payment;
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
@RequestMapping("/redis")
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
    private StringRedisTemplate stringRedisTemplate;
    @Resource
    private PaymentService paymentService;

    /**
     * 根据请求参数，处理相关业务后返回前端json媒体
     *
     * @param requestVO 请求参数的封装VO
     * @return 返回ResponseVO实体
     * @throws IOException 当插入失败时，可能会产生IOException异常
     */
    @RequestMapping("/index")
    public ResponseVO index(@RequestBody RequestVO requestVO) throws IOException {
        System.out.println(requestVO.toString());
        ResponseVO responseVO = new ResponseVO();
        String key = PREFIX + INDEX.getAndIncrement();
        String body = UUID.randomUUID().toString();
        responseVO.setId(key);
        responseVO.setBody(body);
        stringRedisTemplate.opsForValue().set(key,body);
        System.out.println(key);
        boolean flag = stringRedisTemplate.opsForValue().setIfAbsent("test","test",1000L, TimeUnit.MINUTES);
        flag = stringRedisTemplate.opsForValue().setIfAbsent("test","test",1000L, TimeUnit.MINUTES);
        return responseVO;
    }

    @RequestMapping("/index2")
    public ResponseVO index2(@RequestBody RequestVO requestVO) throws IOException {
        System.out.println(requestVO.toString());
        ResponseVO responseVO = new ResponseVO();
        String key = PREFIX + INDEX.getAndIncrement();
        String body = UUID.randomUUID().toString();
        responseVO.setId(key);
        responseVO.setBody(body);
        return responseVO;
    }



    @RequestMapping("/testConsistency")
    public ResponseVO testConsistency(@RequestBody Payment payment){
        ResponseVO responseVO = new ResponseVO();
        int num = paymentService.create(payment);
        responseVO.setBody(""+num);
        List<ResponseVO> list = new ArrayList<>();
        list.add(new ResponseVO());
        return responseVO;
    }

}

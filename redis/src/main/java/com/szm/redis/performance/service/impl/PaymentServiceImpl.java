package com.szm.redis.performance.service.impl;

import com.szm.redis.performance.dao.PaymentDao;
import com.szm.redis.performance.entities.Payment;
import com.szm.redis.performance.service.PaymentService;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * 支付信息的实现类
 *
 * @author 沈子明
 * @date 2020/2/18 10:40
 **/
@Service
public class PaymentServiceImpl implements PaymentService {
    @Resource
    private PaymentDao paymentDao;
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    /**
     * Method Name: create
     * Description: 新增支付信息（实现类）
     *
     * @see PaymentService
     * @param payment 支付信息
     * @return 返回影响的行数
     * @author : 沈子明
     * Modified No ： 001
     * Modified By ： 沈子明
     */
    @Override
    @Transactional
    public int create(Payment payment) {
        int num = paymentDao.create(payment);
        String body = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set("test_"+body ,body);
        return num;
    }

    /**
     * 测试继承父类文档
     * @inheritDoc
     */
    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }
}

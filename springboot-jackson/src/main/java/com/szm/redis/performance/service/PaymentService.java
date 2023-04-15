package com.szm.redis.performance.service;


import com.szm.redis.performance.entities.Payment;

/**
 * 描述：支付信息的接口定义
 * <p>主要包含增加、删除、修改、插叙等
 *
 * @author 沈子明
 * @date 2020/2/18 10:40
 * @version 1.0
 **/
public interface PaymentService {
    /**
     * 新增支付信息
     *
     * @param payment 支付信息
     * @return 返回影响记录行数
     */
    int create(Payment payment);

    /**
     * 根据Id查询
     *
     * @param id 支付信息id
     * @return 返回支付信息
     */
    Payment getPaymentById(Long id);
}

package com.szm.redis.performance.dao;

import com.szm.redis.performance.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PaymentDao {
    /**
     * 新增
     *
     * @param payment 支付信息实体
     * @return 返回影响的记录行数
     */
    int create(Payment payment);

    /**
     * 根据Id查询
     *
     * @param id 支付信息id
     * @return 返回支付信息实体
     */
    Payment getPaymentById(@Param("id") Long id);
}

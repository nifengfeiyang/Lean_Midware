package com.szm.redis.performance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 支付信息的实体
 * @author zzyy
 * @date 2020/2/18 10:22
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment implements Serializable {
    /**
     * 实体id
     */
    private Long id;
    /**
     * 支付流水号
     */
    private String serial;
}

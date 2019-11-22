package com.ruby.rocketmq.demo.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PayInfoRes {
    private String status;

    private String orderId;

    private BigDecimal amt;

    private String info;
}

package com.ruby.rocketmq.demo.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class PayInfoReq  implements JsonInterface {
    @ApiModelProperty(example = "订单号", value = "订单号")
    private String orderNo;

    @ApiModelProperty(example = "订单金额", value = "订单金额")
    private BigDecimal amt;

    @ApiModelProperty(example = "订单信息", value = "订单信息")
    private String info;

    public PayInfoReq(){};

}

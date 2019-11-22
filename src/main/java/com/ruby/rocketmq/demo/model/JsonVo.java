package com.ruby.rocketmq.demo.model;

import com.ruby.rocketmq.demo.model.JsonInterface;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class JsonVo {
    @ApiModelProperty(example = "订单号", value = "订单号")
    private String orderNo;

    @ApiModelProperty(example = "订单信息", value = "订单信息")
    private String info;

    @ApiModelProperty(example = "订单金额", value = "订单金额")
    private BigDecimal amt;

    @ApiModelProperty(example = "收件人信息", value = "收件人信息")
    private String recInfo;

    public JsonVo(){};

}

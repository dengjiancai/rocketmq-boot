package com.ruby.rocketmq.demo;

import com.alibaba.fastjson.JSONObject;
import com.ruby.rocketmq.demo.model.JsonVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@Slf4j
@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    public static void main(String[] args) throws Exception {
        BigDecimal big= new BigDecimal(111);
        JsonVo tmp = new JsonVo();
        tmp.setAmt(big);
        tmp.setInfo("faserf32");
        tmp.setOrderNo("000000000faserf32");
        String json = JSONObject.toJSONString(tmp);

        System.out.println(json);
        JsonVo jsonVo = JSONObject.parseObject(json, JsonVo.class);
        System.out.println(jsonVo);
        System.out.println("--------");
        byte[] bytes = JSONObject.toJSONBytes(tmp);
        System.out.println(bytes);
        JsonVo jsonVo2 = JSONObject.parseObject(bytes, JsonVo.class);
        System.out.println(jsonVo2);


    }
}

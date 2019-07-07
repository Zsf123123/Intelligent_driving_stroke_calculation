package com.muheda.controller;

import com.alibaba.fastjson.JSONObject;
import com.muheda.service.handle.StrokeCalculation;
import com.muheda.utils.StringUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * kafka监听
 * @author
 *
 */
@Component
public class RawDataListener {

    /**
     * 实时获取kafka数据(生产一条，监听生产topic自动消费一条)
     * @param record
     * @throws IOException
     */
    @KafkaListener(topics = {"${kafka.consumer.topic}"})
    public void listen(ConsumerRecord<String, String> record) {

        // 接收传过来的完整的行程
        String value = record.value();
        System.out.println(value);
        if(StringUtil.isEmpty(value)){
            return;
        }

        // 包含了设备号，开始时间，结束时间，主键
        JSONObject  jsonObject =  (JSONObject)JSONObject.parse(value);

        String imei = (String)jsonObject.get("imei");
        String startTime = (String)jsonObject.get("startTime");
        String endTime = (String)jsonObject.get("endTime");
        String primaryKey = (String)jsonObject.get("primaryKey");

        System.out.println(jsonObject);

        new StrokeCalculation().TaskAssignment(imei,startTime,endTime,primaryKey);


    }


}
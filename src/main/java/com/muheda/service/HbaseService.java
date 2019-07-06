package com.muheda.service;

import com.muheda.domain.IntelligentDriveTravel;

import  java.util.*;
import java.util.concurrent.CountDownLatch;

public interface HbaseService {

    /**
     *
     * 获取基础数据，并将任务分配进去
     * @param deviceId
     * @param startTime
     * @param endTime
     * @return
     */
    List<Map<String,String>>  getAllData(String deviceId, String startTime, String endTime);


    /**
     * @desc 获取基础数据
     * @param deviceId
     * @param startTime
     * @param endTime
     * @return
     */
    List<Map<String,String>> getBasicData(String deviceId, String startTime, String endTime);


    /**
     * @desc 获取事件数据
     * @param deviceId
     * @param startTime
     * @param endTime
     * @return
     */
    Map<String, List<Map<String,String>>>  getEventData(String deviceId, String startTime, String endTime);




}

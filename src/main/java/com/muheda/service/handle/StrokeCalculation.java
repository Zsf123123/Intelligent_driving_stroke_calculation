package com.muheda.service.handle;


import com.muheda.domain.Contans;
import com.muheda.domain.IntelligentDriveTravel;
import com.muheda.service.HbaseImpl.HbaseServiceImpl;
import com.muheda.service.HbaseService;
import com.muheda.utils.DateUtils;
import com.muheda.utils.ThreadPoolUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/**
 * @desc 行程计算任务分配
 */
@Service
public class StrokeCalculation {

     private  static Logger logger = LoggerFactory.getLogger(StrokeCalculation.class);



     @Autowired
     HbaseService hbaseService;



    /**
     * @desc 用于任务分配与数据整合，并完成生产-消费者模式中的生产者模式
     * @param deviceId
     * @param startTime
     * @param endTime
     */
     public void TaskAssignment(String deviceId, String startTime, String endTime){

         IntelligentDriveTravel intelligentDriveTravel = new IntelligentDriveTravel();

         /**
          * 设置设备号
          */
         intelligentDriveTravel.setDeviceId(deviceId);


         /**
          * 根据读取基础信息
          */
         List<Map<String, String>> basicData = hbaseService.getBasicData(deviceId, startTime, endTime);

         /**
          * 这里将一些基础信息给传递进去，比如起始的点坐标，起始的时间
          */
         Map<String, List<Map<String, String>>> eventData = hbaseService.getEventData(deviceId, startTime, endTime);

         CountDownLatch countDownLatch = new CountDownLatch(3);

         ExecutorService threadPool = ThreadPoolUtils.getThreadPool();

         /**
          * 开启线程处理（如果该任务不是特别耗时，可以直接执行）一些简单的指标统计
          */
         threadPool.submit(() -> DealWithSampleIndicator(intelligentDriveTravel,basicData,countDownLatch));

         /**
          *  开始线程处理，基础数据指标计算
          */
         threadPool.submit(() -> DealWithBasicData(intelligentDriveTravel,basicData, countDownLatch));

         /**
          * 开启线程处理，事件统计指标
          */
         threadPool.submit(() -> DealWithEventData(intelligentDriveTravel,eventData,countDownLatch));


         /**
          * 使用CountDownLatch，将处理的结果存储到队列中
          *
          */
         try {
             countDownLatch.await();
         } catch (InterruptedException e) {
             e.printStackTrace();
         }


         // 将处理完成的数据发送到缓存队列
         CacheQueueCenter.cacheQueue.add(intelligentDriveTravel);

     }


    /**
     * @desc  通过基础数据计算出一些基础的指标
     *    起始经纬度
     *    结束经纬度
     *    起始时间
     *    结束时间
     *    驾驶时长
     * @param intelligentDriveTravel
     * @param basicData
     * @param countDownLatch
     */
     public void DealWithSampleIndicator(IntelligentDriveTravel intelligentDriveTravel, List<Map<String, String>> basicData ,CountDownLatch countDownLatch){


         Map<String, String> firstPoint = basicData.get(0);
         Map<String, String> lastPoint = basicData.get(basicData.size() - 1);

         /**
          * 起点经纬度
          */
         intelligentDriveTravel.setStartLng( Double.parseDouble(firstPoint.get("lng")));
         intelligentDriveTravel.setStartLat(Double.parseDouble(firstPoint.get("lat")));

         /**
          * 终点的经纬度
          */
         intelligentDriveTravel.setEndLng( Double.parseDouble(lastPoint.get("lng")));
         intelligentDriveTravel.setEndLat(Double.parseDouble(lastPoint.get("lat")));


         /**
          * 起点终点时间
          */
         String startTime = firstPoint.get("time");
         Date startDate = DateUtils.timeFormat(Contans.DATE_FORMART_SECONDS, startTime);
         intelligentDriveTravel.setStartTime(startDate);

         String endTime = lastPoint.get("time");
         Date endDate = DateUtils.timeFormat(Contans.DATE_FORMART_SECONDS, endTime);
         intelligentDriveTravel.setEndTime(endDate);


         /**
          * 驾驶时长（单位是秒）
          */
         int driveTime = DateUtils.getDiffDate(startDate, endDate, Calendar.SECOND);
         intelligentDriveTravel.setDriveTime(driveTime);

         countDownLatch.countDown();

     }





    /**
     * @desc 处理基础数据，获取指标
     */
    public void DealWithBasicData(IntelligentDriveTravel intelligentDriveTravel, List<Map<String, String>> basicData ,CountDownLatch countDownLatch){

        try {
            //驾驶里程，高速里程，市区里程，国道里程，行车三急,市区的里程
            RoadSplit roadSplit = new RoadSplit();
            Map<String, Object> map = roadSplit.splitRoad(basicData);

            /**
             * 将返回的map根据字段反射传入的对象中
             */
            reflectObjAssigment(intelligentDriveTravel, map);

        }catch (Exception e){

            e.printStackTrace();
         }finally {
            countDownLatch.countDown();
        }


    }


    /**
     * @desc 计算不同该段行程的行驶里程
     * @param basicData
     * @return  返回的是该行程行驶在不同路上的里程
     */
    public Map<String,String> CalculateDifferentMileage(List<Map<String,String>> basicData){





        return null;
    }



    /**
     * @desc 统计相关指标
     * @param map
     * @return
     */
    public Map<String,Object>  countEvent(Map<String,List<Map<String,String>>> map){

        Map<String, Object> resultMap = new HashMap<>();

        Map<String, Map<String, String>> eventParamsMapping = HbaseServiceImpl.eventParamsMapping;

        for (Map.Entry<String, List<Map<String, String>>> entry : map.entrySet()) {

            String key = entry.getKey();

            Map<String, String> map1 = eventParamsMapping.get(key);

            List<Map<String, String>> value = entry.getValue();

            for (Map<String,String>  m: value) {

                for (Map.Entry<String,String>  en: m.entrySet()) {


                    //这里对相关字段进行了累加，有的设备是直接进行+1 操作就可以了，有的需要进行数据累计
                    String k = map1.get(en.getKey());


//                   resultMap.put(k , en.getValue()  + resultMap.get(k) );

                    Integer sum = (Integer) resultMap.get(k);


                    if(sum == null){

                        resultMap.put(k , 1);
                    }else {

                        sum ++;
                        resultMap.put(k, sum);
                    }


                }

            }


        }


        return resultMap;

    }


    /**
     * @desc 处理事件数据
     */
    public void DealWithEventData(IntelligentDriveTravel intelligentDriveTravel,Map<String, List<Map<String, String>>> eventData,CountDownLatch countDownLatch){

        try {

            // 计算急加速，急减速，急转弯， 车道偏离，超速次数， 变道次数，前车预警
            Map<String, Object> map = countEvent(eventData);

            // 使用反射将数据赋值到对象中去
            reflectObjAssigment(intelligentDriveTravel,map);
            countDownLatch.countDown();
        }catch (Exception e){
            e.printStackTrace();

        }finally {
            countDownLatch.countDown();
        }

    }



    /**
     * @desc 使用反射将对象进行赋值
     * @return
     */
    public IntelligentDriveTravel reflectObjAssigment(IntelligentDriveTravel intelligentDriveTravel, Map<String,Object> map) {

        Set<String> set = map.keySet();
        Class<? extends IntelligentDriveTravel> aClass = intelligentDriveTravel.getClass();

        Field[] declaredFields = aClass.getDeclaredFields();

        for (Field field : declaredFields) {

            field.setAccessible(true);
            String key = field.getName();

            if(set.contains(key)){
                try {
                    field.set(intelligentDriveTravel,map.get(key));
                } catch (IllegalAccessException e) {

                    e.printStackTrace();
                }
            }

        }




        return intelligentDriveTravel;
    }



    @Test
    public  void test1() {

        HbaseServiceImpl hbaseService = new HbaseServiceImpl();
        Map<String, List<Map<String, String>>> eventData = hbaseService.getEventData("1000033379", "2019", "2020");

        IntelligentDriveTravel intelligentDriveTravel = new IntelligentDriveTravel();

        DealWithEventData(intelligentDriveTravel,eventData,new CountDownLatch(1));




    }
}

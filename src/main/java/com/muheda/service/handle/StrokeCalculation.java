package com.muheda.service.handle;


import com.muheda.domain.Contans;
import com.muheda.domain.IntelligentDriveTravel;
import com.muheda.service.HbaseImpl.HbaseServiceImpl;
import com.muheda.service.HbaseService;
import com.muheda.utils.DateUtils;
import com.muheda.utils.StringUtil;
import com.muheda.utils.ThreadPoolUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.function.Function;

/**
 * @desc 行程计算任务分配
 */
@Service
public class StrokeCalculation {

    private  static Logger logger = LoggerFactory.getLogger(StrokeCalculation.class);




    /**
     * @desc 用于任务分配与数据整合，并完成生产-消费者模式中的生产者模式
     * @param deviceId
     * @param startTime
     * @param endTime
     */
    public  void TaskAssignment(String deviceId, String startTime, String endTime,String primaryKey){

        // 后续改为自动注入
        HbaseService hbaseService = new HbaseServiceImpl();

        /**
         * 最后需要存储到数据库中的对象
         */
        IntelligentDriveTravel intelligentDriveTravel = new IntelligentDriveTravel();


        /**
         * 设置设备号
         */
        intelligentDriveTravel.setDeviceId(deviceId);

        /**
         * 设置主键
         */
        intelligentDriveTravel.setPrimarykey(primaryKey);

        /**
         * 根据读取基础信息
         */
        List<Map<String, String>> basicData = hbaseService.getBasicData(deviceId, startTime, endTime);

        /**
         * 这里将一些基础信息给传递进去，比如起始的点坐标，起始的时间
         */
        Map<String, List<Map<String, String>>> eventData = hbaseService.getEventData(deviceId, startTime, endTime);

        /**
         * 设置门栓
         */
        CountDownLatch countDownLatch = new CountDownLatch(3);

        ExecutorService threadPool = ThreadPoolUtils.getThreadPool();

        /**
         * 开启线程处理（如果该任务不是特别耗时，可以直接执行）一些简单的指标统计
         */
        DealWithSampleIndicator(intelligentDriveTravel,basicData,countDownLatch);
//        threadPool.submit(() -> DealWithSampleIndicator(intelligentDriveTravel,basicData,countDownLatch));

        /**
         *  开始线程处理，基础数据指标计算
         */
        DealWithBasicData(intelligentDriveTravel,basicData, countDownLatch);
//        threadPool.submit(() -> DealWithBasicData(intelligentDriveTravel,basicData, countDownLatch));

        /**
         * 开启线程处理，事件统计指标
         */
        DealWithEventData(intelligentDriveTravel,eventData,countDownLatch);
//        threadPool.submit(() -> DealWithEventData(intelligentDriveTravel,eventData,countDownLatch));


        /**
         * 使用CountDownLatch，将处理的结果存储到队列中
         *
         */
/*        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/


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
        intelligentDriveTravel.setStartLng( Double.parseDouble(firstPoint.get("lnt")));
        intelligentDriveTravel.setStartLat(Double.parseDouble(firstPoint.get("lat")));

        /**
         * 终点的经纬度
         */
        intelligentDriveTravel.setEndLng( Double.parseDouble(lastPoint.get("lnt")));
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

        /**
         * 查找出相应字段的映射关系，等待后续从hbse中查出的数据进行查询
         */
        Map<String, Map<String, Map<String, String>>> eventParamsMapping = HbaseServiceImpl.eventParamsMapping;


        for (Map.Entry<String, List<Map<String, String>>> entry : map.entrySet()) {

            /**
             * 列簇名
             */
            String key = entry.getKey();

            /**
             * 查找列簇名对应的关系
             */
            Map<String, Map<String, String>> map1 = eventParamsMapping.get(key);

            /**
             * 该列簇下面的所有数据，因为是从hbase 中查询出来的，所以可能会查出一些无关数据，这些数据后续会过滤
             */
            List<Map<String, String>> value = entry.getValue();

            for (Map<String,String>  m: value) {

                for (Map.Entry<String,String>  en: m.entrySet()) {

                    /**
                     * 通过这些字段去映射关系中拿去相应的映射以及校验规则
                     */
                    String column = en.getKey();
                    String enValue = en.getValue();
                    Map<String, String>  columnMappingInfo = map1.get(column);

                    /**
                     * 如果找不到映射规则,说明该字段是不需要的，直接filter
                     */
                    if(columnMappingInfo == null){
                        continue;
                    }

                    /**
                     * 该字段映射到你最终的结果统计字段
                     */
                    String mapping = columnMappingInfo.get("mapping");

                    /**
                     * 该字段的数据所对应的校验的方式
                     */
                    String check = columnMappingInfo.get("check");

                    /**
                     * 将该字段所对应的校验的函数给取出来，并将数据传入函数中。判断该数据是否符合
                     */
                    Function<Object, Boolean> function = DataCheck.checkMap.get(check);

                    Boolean aBoolean = null;
                    try {

                        aBoolean = function.apply(enValue);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    if (aBoolean  == false){
                        continue;
                    }

                    /**
                     * 如果该字段所对应的值符合条件之后进行相应的统计
                     */
                    String countType = columnMappingInfo.get("count");

                    if(StringUtil.isEmpty(countType)){

                        logger.error("请检查配置文件！！！，没有正确的校验方式！！");
                    }

                    Integer num = (Integer) resultMap.get(mapping);

                    int i  = Integer.parseInt(enValue);

                    if(num == null ){
                        resultMap.put(mapping, i);
                        continue;
                    }

                    /**
                     * 进行数值的累加
                     */
                    if("Accumulate".equals(countType)){
                        resultMap.put(mapping, num + i);
                        continue;
                    }

                    /**
                     * + 1 操作
                     */
                    if("plusOne".equals(countType)){
                        resultMap.put(mapping, ++num);
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



}

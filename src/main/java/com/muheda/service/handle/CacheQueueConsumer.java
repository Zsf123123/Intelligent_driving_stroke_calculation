package com.muheda.service.handle;

import com.muheda.domain.IntelligentDriveTravel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @desc 将之前已经完成一部分指标的数据从队列中进行消费。进行外部接口的调用
 *
 */
public class CacheQueueConsumer implements Runnable{


    private static final Logger LOGGER = LoggerFactory.getLogger(CacheQueueConsumer.class);

    @Override
    public void run() {

        LinkedBlockingQueue<IntelligentDriveTravel> cacheQueue = CacheQueueCenter.cacheQueue;

        for(;;){

            IntelligentDriveTravel intelligentDriveTravel = cacheQueue.remove();

            /**
             * 通过远程调用获取该行驶点的起点位置信息
             */
            Double startLng = intelligentDriveTravel.getStartLng();
            Double startLat = intelligentDriveTravel.getStartLat();

            /**
             * 通过远程调用获取该行驶点的终点位置信息
             */
            Double endLng = intelligentDriveTravel.getEndLng();
            Double endLat = intelligentDriveTravel.getEndLat();


            /**             * 急加速次数，急减速次数，行程开始时间，行程时长，超速次数

             */
            Integer deviceSpeedUpTimes = intelligentDriveTravel.getDeviceSpeedUpTimes();
            Integer deviceSpeedDownTimes = intelligentDriveTravel.getDeviceSpeedDownTimes();
            Integer deviceSharpTurnTimes = intelligentDriveTravel.getDeviceSharpTurnTimes();
            Date startTime = intelligentDriveTravel.getStartTime();
            Integer driveTime = intelligentDriveTravel.getDriveTime();
            Integer overSpeedTimes = intelligentDriveTravel.getOverSpeedTimes();


            //将数据存储到mysql中


        }

    }

}

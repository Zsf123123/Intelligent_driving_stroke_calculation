package com.muheda.service.handle;

import com.muheda.domain.IntelligentDriveTravel;

import java.util.concurrent.LinkedBlockingQueue;

public class CacheQueueCenter {


    /**
     * 全局缓存消息对
     */
    public  static  final LinkedBlockingQueue<IntelligentDriveTravel> cacheQueue =  new  LinkedBlockingQueue();




}

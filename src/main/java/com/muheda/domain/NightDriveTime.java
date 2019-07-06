package com.muheda.domain;

import com.alibaba.fastjson.JSON;
import com.muheda.utils.ReadProperty;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @desc 用于获取每个月的不同的夜间时间
 *
 * 夜间里程时间：
 * 3、4、5月：18：30-06：30
 * 6、7、8月：19：30-05：30
 * 9、10、11月：18：30-06：30
 * 12、1、2月：17：30-07：30
 *
 */
public class NightDriveTime {


    public String  startTime;

    public String endTime;

    private  static  Map<String,NightDriveTime> nightTime = new HashMap();

    static {

        String fileContent = ReadProperty.getFileContent(Contans.NIGHT_TIME_MAPPING_MONTH);

        Map<String, Object> parse = (Map<String, Object>) JSON.parse(fileContent);

        for (Map.Entry<String,Object> entry: parse.entrySet()) {
            String key = entry.getKey();

            JSON value = (JSON)entry.getValue();
            NightDriveTime nightDriveTime = value.toJavaObject(NightDriveTime.class);
            nightTime.put(key, nightDriveTime);
        }

    }

    public NightDriveTime() {

    }

    private NightDriveTime(String startTime, String endTime){
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public static NightDriveTime getInstance(String month){

        NightDriveTime nightDriveTime = nightTime.get(String.valueOf(month));

        return nightDriveTime;
    }




}

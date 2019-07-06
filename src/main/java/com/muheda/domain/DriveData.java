package com.muheda.domain;

import java.util.ArrayList;

public class DriveData {

    // 保存异常数据
    private ArrayList<Double> checkValue;
    // 保存异常数据时间
    private ArrayList<Long> checkTime;
    // 保存异常数据类型
    private ArrayList<Integer> checkType;

    public DriveData(){
        checkValue = new ArrayList<>();
        checkTime = new ArrayList<>();
        checkType = new ArrayList<>();
    }

    public DriveData addData(double val, long time, int type){
        checkTime.add(time);
        checkValue.add(val);
        checkType.add(type);

        return this;
    }

    public ArrayList<Double> getCheckValue(){
        return this.checkValue;
    }

    public ArrayList<Long> getCheckTime(){
        return this.checkTime;
    }

    public ArrayList<Integer> getCheckType(){
        return this.checkType;
    }
}


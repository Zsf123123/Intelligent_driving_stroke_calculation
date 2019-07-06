package com.muheda.domain;


import java.io.Serializable;
import java.util.Date;


/**
 * @desc 行程统计实体类
 *
 */
public class IntelligentDriveTravel implements Serializable {


    /**
     * 从kafka获取时传入的主键
     */
    private  String  primaryKey;


    /**
     * 设备号
     *
     */
    private String deviceId;


    /**
     * 设备类型
     *
     */
    private Integer deviceType;


    /**
     *驾驶里程
     *
     */
    private Integer driveLength;


    /**
     * 驾驶时长
     *
     */
    private Integer driveTime;

    /**
     * 市区驾驶里程 单位：km
     *
     */
    private  Integer  urbanDriveLength;

    /**
     * 国道里程 单位：km
     */
    private Integer  nationalRoadDriveLength;


    /**
     *高速里程 单位：km
     *
     */
    private  Double  highSpeedDriveLength;


    /**
     * 夜间行驶里程 单位：km
     */
    private  Double nightDriveLength;

    /**
     * 急加速次数
     */
    private Integer  speedUpTimes;

    /**
     * 急减速次数
     *
     */
    private Integer speedDownTimes;


    /**
     * 急转弯次数
     *
     */
    private  Integer sharpTurnTimes;


    /**
     * 设备急加速次数
     */
    private Integer  deviceSpeedUpTimes;

    /**
     * 设备急减速次数
     *
     */
    private Integer deviceSpeedDownTimes;


    /**
     * 设备急转弯次数
     *
     */
    private  Integer deviceSharpTurnTimes;


    /**
     * 车道偏移次数
     */
    private  Integer  laneShiftTimes;


    /**
     * 超速次数
     */
    private  Integer  overSpeedTimes;


    /**
     * 变道次数
     */
    private  Integer laneChangeTimes;


    /**
     *前车预警
     */
    private  Integer prevehicleWarnTimes;


    /**
     *驾驶得分
     */
    private  Integer driveScore;


    /**
     *行程开始时间
     */
    private Date startTime;


    /**
     *行程结束时间
     */
    private  Date endTime;


    /**
     *行车的起始的经度
     */
    private  Double startLng;

    /**
     * 行车起始的纬度
     *
     */
    private  Double startLat;


    /**
     * 行车终点的纬度
     */
    private  Double endLng;


    /**
     *行车终点的纬度
     */
    private  Double endLat;


    /**
     * 起点位置
     */
    private String  startPosition;

    /**
     * 终点位置
     */
    private  String endPosition;


    /**
     * 最高时速
     */
    private  Double  highestSpeed;

    /**
     * 创建时间
     */
    private  Date createTime;


    public String getPrimarykey() {
        return primaryKey;
    }

    public void setPrimarykey(String primarykey) {
        this.primaryKey = primarykey;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getDriveLength() {
        return driveLength;
    }

    public void setDriveLength(Integer driveLength) {
        this.driveLength = driveLength;
    }

    public Integer getDriveTime() {
        return driveTime;
    }

    public void setDriveTime(Integer driveTime) {
        this.driveTime = driveTime;
    }

    public Integer getUrbanDriveLength() {
        return urbanDriveLength;
    }

    public void setUrbanDriveLength(Integer urbanDriveLength) {
        this.urbanDriveLength = urbanDriveLength;
    }

    public Integer getNationalRoadDriveLength() {
        return nationalRoadDriveLength;
    }

    public void setNationalRoadDriveLength(Integer nationalRoadDriveLength) {
        this.nationalRoadDriveLength = nationalRoadDriveLength;
    }

    public Double getHighSpeedDriveLength() {
        return highSpeedDriveLength;
    }

    public void setHighSpeedDriveLength(Double highSpeedDriveLength) {
        this.highSpeedDriveLength = highSpeedDriveLength;
    }

    public Double getNightDriveLength() {
        return nightDriveLength;
    }

    public void setNightDriveLength(Double nightDriveLength) {
        this.nightDriveLength = nightDriveLength;
    }

    public Integer getSpeedUpTimes() {
        return speedUpTimes;
    }

    public void setSpeedUpTimes(Integer speedUpTimes) {
        this.speedUpTimes = speedUpTimes;
    }

    public Integer getSpeedDownTimes() {
        return speedDownTimes;
    }

    public void setSpeedDownTimes(Integer speedDownTimes) {
        this.speedDownTimes = speedDownTimes;
    }

    public Integer getSharpTurnTimes() {
        return sharpTurnTimes;
    }

    public void setSharpTurnTimes(Integer sharpTurnTimes) {
        this.sharpTurnTimes = sharpTurnTimes;
    }

    public Integer getDeviceSpeedUpTimes() {
        return deviceSpeedUpTimes;
    }

    public void setDeviceSpeedUpTimes(Integer deviceSpeedUpTimes) {
        this.deviceSpeedUpTimes = deviceSpeedUpTimes;
    }

    public Integer getDeviceSpeedDownTimes() {
        return deviceSpeedDownTimes;
    }

    public void setDeviceSpeedDownTimes(Integer deviceSpeedDownTimes) {
        this.deviceSpeedDownTimes = deviceSpeedDownTimes;
    }

    public Integer getDeviceSharpTurnTimes() {
        return deviceSharpTurnTimes;
    }

    public void setDeviceSharpTurnTimes(Integer deviceSharpTurnTimes) {
        this.deviceSharpTurnTimes = deviceSharpTurnTimes;
    }

    public Integer getLaneShiftTimes() {
        return laneShiftTimes;
    }

    public void setLaneShiftTimes(Integer laneShiftTimes) {
        this.laneShiftTimes = laneShiftTimes;
    }

    public Integer getOverSpeedTimes() {
        return overSpeedTimes;
    }

    public void setOverSpeedTimes(Integer overSpeedTimes) {
        this.overSpeedTimes = overSpeedTimes;
    }

    public Integer getLaneChangeTimes() {
        return laneChangeTimes;
    }

    public void setLaneChangeTimes(Integer laneChangeTimes) {
        this.laneChangeTimes = laneChangeTimes;
    }

    public Integer getPrevehicleWarnTimes() {
        return prevehicleWarnTimes;
    }

    public void setPrevehicleWarnTimes(Integer prevehicleWarnTimes) {
        this.prevehicleWarnTimes = prevehicleWarnTimes;
    }

    public Integer getDriveScore() {
        return driveScore;
    }

    public void setDriveScore(Integer driveScore) {
        this.driveScore = driveScore;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getStartLng() {
        return startLng;
    }

    public void setStartLng(Double startLng) {
        this.startLng = startLng;
    }

    public Double getStartLat() {
        return startLat;
    }

    public void setStartLat(Double startLat) {
        this.startLat = startLat;
    }

    public Double getEndLng() {
        return endLng;
    }

    public void setEndLng(Double endLng) {
        this.endLng = endLng;
    }

    public Double getEndLat() {
        return endLat;
    }

    public void setEndLat(Double endLat) {
        this.endLat = endLat;
    }

    public String getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(String startPosition) {
        this.startPosition = startPosition;
    }

    public String getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(String endPosition) {
        this.endPosition = endPosition;
    }

    public Double getHighestSpeed() {
        return highestSpeed;
    }

    public void setHighestSpeed(Double highestSpeed) {
        this.highestSpeed = highestSpeed;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}

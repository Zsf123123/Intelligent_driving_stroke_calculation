package com.muheda.domain;


import com.muheda.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;


public class Point {

    private  static Logger logger = LoggerFactory.getLogger(Point.class);

    private Double lng;
    private Double lat;
    private Date time;

    public Point(){

    }


    public Point(String lng, String lat, String time){

        try {
            this.lng = Double.parseDouble(lng);
            this.lat = Double.parseDouble(lat);
            this.time = DateUtils.parseTime(time);
        }catch (Exception ex){
            logger.error("Point 对象初始化异常");
             ex.printStackTrace();
        }

    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

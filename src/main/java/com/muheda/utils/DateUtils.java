package com.muheda.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {

    public static final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final String DATETIME_DAY = "yyyyMMdd";

    private  static Logger logger = LoggerFactory.getLogger(DateUtils.class);

    /**
     * 时间相减
     *
     * @param strDateBegin
     * @param strDateEnd
     * @param iType
     * @return
     */
    public static int getDiffDate(Date strDateBegin, Date strDateEnd, int iType) {
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(strDateBegin);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(strDateEnd);
        long lBegin = calBegin.getTimeInMillis();
        long lEnd = calEnd.getTimeInMillis();
        if (iType == Calendar.SECOND)
            return (int) ((lEnd - lBegin) / 1000L);
        if (iType == Calendar.MINUTE)
            return (int) ((lEnd - lBegin) / 60000L);
        if (iType == Calendar.HOUR)
            return (int) ((lEnd - lBegin) / 3600000L);
        if (iType == Calendar.DAY_OF_MONTH) {
            return (int) ((lEnd - lBegin) / 86400000L);
        }
        return -1;
    }



    public static Date timeFormat(String form,String str) {

        Date parse = null;
        SimpleDateFormat format = new SimpleDateFormat(form);

        try {
            parse = format.parse(str);
        } catch (ParseException e) {
            logger.error("时间格式转换失败");
            e.printStackTrace();
        }

        return parse;
    }


    /**
     * @desc  将Date 格式转化成String
     * @param dateDate
     * @return
     */
    public static String dateToStrLong(String formart,Date dateDate) {

        SimpleDateFormat formatter = new SimpleDateFormat(formart);
        String dateString = formatter.format(dateDate);

        return dateString;
    }



    /**
     * @desc 获取当前时间
     */
    public static String getTodayTime(String str){

        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat(str);
        return dateFormat.format(date);

    }


    /**
     * @desc 获取传入时间之前一天的时间
     * @param specifiedDay
     * @return
     */
    public static String getTheDayBeforeYesterday(String specifiedDay){

        //SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Date date=null;
        try {
            date = new SimpleDateFormat(DATETIME_DAY).parse(specifiedDay);
        } catch (ParseException e) {
            logger.error("时间转换异常");
            e.printStackTrace();
        }
        c.setTime(date);
        int day=c.get(Calendar.DATE);
        c.set(Calendar.DATE,day-1);
        String dayBefore=new SimpleDateFormat(DATETIME_DAY).format(c.getTime());

        return dayBefore;

    }


    /**
     * 解析时间字符串
     * @param time 时间字符串
     * @return Date
     */
    public static Date parseTime(String time) {
        try {
            return TIME_FORMAT.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

}

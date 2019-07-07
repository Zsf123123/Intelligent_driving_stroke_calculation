package com.muheda.service.handle;


import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author zhangshaofan
 * @desc用于对时间数据额校验映射
 *
 */
public class DataCheck {

    public static Map<String, Function<Object, Boolean>> checkMap = new HashMap<>();


    static {

        checkMap.put("num",DataCheck::validatelNum);
        checkMap.put("preCarWarnDistance",DataCheck::validatePreCarWarnDistance);

    }


    /**
     * @desc 前车预警
     * @return
     */
    public static  boolean validatePreCarWarnDistance(Object obj){

        if(obj == null){
            return  false;
        }

        try {
            String  num= (String) obj;

            /**
             *  如果前车预警达到某个警戒值，则表示该警告有效
             */
            if(Integer.parseInt(num) >= 1){
                return  true;
            }

        }catch (Exception ex){
            ex.printStackTrace();
            return  false;
        }


        return  false;
    }



    /**
     * @desc 验证该数据是否是一个数字，并且该数字不可以为0 用于一些次数的统计
     *
     */
    public static boolean validatelNum(Object obj) {

        if(obj == null){
            return  false;
        }

        try {
            String  num= (String) obj;
            if(Integer.parseInt(num) <= 0){
                return  false;
            }

        }catch (Exception ex){
            ex.printStackTrace();
            return  false;
        }

        return true;

    }





}

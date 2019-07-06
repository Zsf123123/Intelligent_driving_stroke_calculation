package com.muheda.utils;

import com.alibaba.fastjson.JSONObject;
import com.muheda.domain.Contans;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @desc 读取配置文件类，获取application.properties的信息
 */
public class ReadProperty {
    private static Log logger = LogFactory.getLog(ReadProperty.class);
    /**
     * 系统配置变量
     */
    private static volatile Map<String, String> confDataMap = null;

    /**
     * 获取系统变量
     *
     * @param key
     * @return
     */
    public static String getConfigData(String key) {

        if (confDataMap != null) {
            return confDataMap.get(key);
        }

        InputStream sysInputStream = null;
        InputStream devInputStream = null;
        confDataMap = new ConcurrentHashMap<>(16);
        try {

            sysInputStream = ReadProperty.class.getResourceAsStream("/application.properties");

            Properties prop = new Properties();

            prop.load(sysInputStream);

            String valueString = prop.getProperty("sys.config.file");
            if ("application.properties".equals(valueString)) {
                Iterator<String> it = prop.stringPropertyNames().iterator();
                String proKeyString;
                while (it.hasNext()) {
                    proKeyString = it.next();
                    confDataMap.put(proKeyString, prop.getProperty(proKeyString));
                }
                return confDataMap.get(key);
            }

            // 若非正式环境下的配置文件
            confDataMap.clear();
            devInputStream = ReadProperty.class.getResourceAsStream("/" + valueString);
            prop = new Properties();
            prop.load(devInputStream);
            Iterator<String> it = prop.stringPropertyNames().iterator();
            String proKeyString;
            while (it.hasNext()) {
                proKeyString = it.next();
                confDataMap.put(proKeyString, prop.getProperty(proKeyString));
            }
            return confDataMap.get(key);

        } catch (Exception e) {
            logger.error(e);
            return null;
        } finally {
            try {
                if (sysInputStream != null) {
                    sysInputStream.close();
                }
                if (devInputStream != null) {
                    devInputStream.close();
                }
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }


    /**
     * 读取文件全部的的内容，如果该文件较大不建议使用该方法
     * @param projectName
     * @return
     */
    public static String getFileContent(String projectName){

        InputStream in = null;
        try{
            in = ReadProperty.class.getResourceAsStream(projectName);
            byte b[] = new byte[1024*100];
            int len = 0;
            int temp=0;          //所有读取的内容都使用temp接收
            while((temp=in.read())!=-1){    //当没有读取完时，继续读取
                b[len]=(byte)temp;
                len++;
            }
            in.close();
            return new String(b,0,len);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                logger.error(e);
            }
        }
    }


    /**
     * @desc 获取基础数据配置信息基础对象
     * @return
     */
    public static Map<String,Object> getBasicConfig(){

        Map<String, Object> resultMap = new HashMap<>(1);

        String basicDataInfo = getFileContent(Contans.HBASE_INFO_JSONFILE_PATH);
        String family = null;
        List<String> need = null;

        try {
            JSONObject jsonObject = JSONObject.parseObject(basicDataInfo);
            Map<String, Object> basic = (Map<String, Object>) jsonObject.get("basic");
            family = (String)basic.get("family");

            resultMap.put("family",family);
            //获取需要的字段数据
            need = (List<String>)basic.get("need");
            resultMap.put("need",need);

        }catch (Exception ex){
            ex.printStackTrace();
        }

        resultMap.put(family, need);

        return resultMap;

    }


    /**
     * @desc 获取事件数据相关配置信息
     *
     */
    public static Map<String, Map<String,String>> getEventConfig(){

        Map<String, Map<String, String>> eventMap = null;
        try {
            String eventDataInfo = getFileContent(Contans.HBASE_INFO_JSONFILE_PATH);
            JSONObject jsonObject = JSONObject.parseObject(eventDataInfo);
            eventMap = (Map<String, Map<String, String>>) jsonObject.get("event");
        }catch (Exception e){
            e.printStackTrace();
        }

        return  eventMap;
    }








}
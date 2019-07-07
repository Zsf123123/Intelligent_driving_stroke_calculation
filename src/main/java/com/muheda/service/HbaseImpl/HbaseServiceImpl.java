package com.muheda.service.HbaseImpl;

import com.muheda.dao.hbase.HbaseDao;
import com.muheda.domain.Contans;
import com.muheda.service.HbaseService;
import com.muheda.utils.ReadProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HbaseServiceImpl implements HbaseService {

    @Autowired
    public  HbaseDao hbaseDao = new HbaseDao();

    private  static String tableName = ReadProperty.getConfigData(Contans.HBASE_TABLE_NAME);

    private  static  String pre = ReadProperty.getConfigData(Contans.HBASE_ROWKEY_PRE);

    /**
     * 基础数据配置解析后的json
     */
    private  static   Map<String,Object>  basicConfigJson = ReadProperty.getBasicConfig();

    /**
     * 事件数据解析后的json
     */
    private  static   Map<String,Map<String,Map<String,String>>>  eventConfigJson = ReadProperty.getEventConfig();


    /**
     * 关于取出hbase数据的字段以及相关映射
     */
    public   static   Map<String,Map<String,Map<String,String>>>  eventParamsMapping  = new HashMap<String, Map<String, Map<String,String>>>();


    static {

        // 初始化事件指标
        for (Map.Entry<String,Map<String,Map<String,String>>> entry : eventConfigJson.entrySet()) {

            String family = entry.getKey();

            Map<String, Map<String,String>> map = new HashMap<>();

            for (Map.Entry<String,Map<String,String>> entry1 : entry.getValue().entrySet()) {

                map.put(entry1.getKey(),entry1.getValue());

                eventParamsMapping.put(family,map);

            }

        }

    }

    @Override
    public List<Map<String, String>> getAllData(String deviceId, String startTime, String endTime) {

        /**
         * 获取基础数据和事件数据
         */
        List<Map<String, String>> basicData = getBasicData(deviceId,startTime, endTime);

        Map<String, List<Map<String, String>>> eventData = getEventData(deviceId, startTime, endTime);


        return null;

    }



    public List<Map<String,String>> getBasicData(String deviceId, String startTime, String endTime){

        String startRowKey = pre + "_" +  deviceId + "_" + startTime;
        String endRowKey = pre + "_" +  deviceId + "_" + endTime + "z";

        String  basicFamily = (String)basicConfigJson.get("family");
        List<String> list = (List<String>) basicConfigJson.get("need");

        //读取json文件中基础数据对应的basic和所需要的列
        List<Map<String, String>> basicData = hbaseDao.GetSpecifiedColumn(tableName, basicFamily, startRowKey, endRowKey, list);

        return basicData;

    }


    /**
     * @desc  查询所需的数据from hbase
     * @param deviceId
     * @param startTime
     * @param endTime
     * @return
     */
    public Map<String, List<Map<String,String>>>  getEventData(String deviceId, String startTime, String endTime){

        String startRowKey = pre + "_" +  deviceId + "_" + startTime;
        String endRowKey = pre + "_" +  deviceId + "_" + endTime + "z";


        Map<String, List<Map<String, String>>> resultMap = new HashMap<>();

        for (Map.Entry<String, Map<String, Map<String,String>>> entry : eventConfigJson.entrySet()) {

            String family = entry.getKey();

            Map<String, Map<String, String>> map = entry.getValue();

            List<String> list = new ArrayList<>();

            for (Map.Entry<String,Map<String,String>>  entry1: map.entrySet()) {

                /**
                 * 获取该列簇所需要的column
                 */
                String key = entry1.getKey();
                list.add(key);
            }


            List<Map<String, String>> basicData = hbaseDao.GetSpecifiedColumn(tableName, family, startRowKey, endRowKey, list);

            resultMap.put(family,basicData);
        }

        return resultMap;
    }




    public static void main(String[] args) {


        for (Map.Entry<String,Map<String,Map<String,String>>> entry : eventConfigJson.entrySet()) {

            String family = entry.getKey();

            Map<String, Map<String,String>> map = new HashMap<>();

            for (Map.Entry<String,Map<String,String>>  entry1 : entry.getValue().entrySet()) {

                map.put(entry1.getKey(),entry1.getValue());

                eventParamsMapping.put(family,map);

            }

        }

        System.out.println(eventParamsMapping);
    }




}

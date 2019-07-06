package com.muheda.domain;

import com.alibaba.fastjson.JSONObject;
import com.muheda.utils.ReadProperty;

import java.util.Map;


public class Roadlevel {

    public static Map<String, Map<String,String>> roadLevelMapping;

    static {
        //加载道路等级的映射关系
        String roadLevel = ReadProperty.getFileContent(Contans.ROAD_MAPPING_JSONFILE_PATH);
        roadLevelMapping = (Map<String, Map<String,String>>) JSONObject.parse(roadLevel);
    }


}

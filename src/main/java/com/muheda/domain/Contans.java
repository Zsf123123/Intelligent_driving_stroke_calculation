package com.muheda.domain;



/**
 * @author
 * @parse
 * @Description 常量类
 * @Date: Created in 14:03 2018/5/29
 * @params
 */

public interface Contans {

    /**
     * 判断数据是否为空校验
     */
    String NULL = "null";

    /**
     * 该项目的hbase 表名
     */
    String HBASE_TABLE_NAME = "hbase.table.name";

    /**
     * 该项目存储在hbase中的rowkey前缀
     *
     */
    String HBASE_ROWKEY_PRE = "hbase.rowkey.pre";

    /**
     * 读取hbase具体数据的配置文件名
     *
     */
    String HBASE_INFO_JSONFILE_PATH =  "/info.json";

    /**
     * 读取道路映射信息的配置文件名
     */
    String ROAD_MAPPING_JSONFILE_PATH = "/roadLevel.json";

    /**
     * 读取月份映射的文件地址名
     */
    String NIGHT_TIME_MAPPING_MONTH = "/nightTime.json";

    /**
     * thrift 服务地址
     */
    String RPC_THRIFT_ADDRESS = "rpc.thrift.address";

    /**
     *thrift 服务端口
     */
    String RPC_THRIFT_PORT = "rpc.thrift.port";

    /**
     * thrift 超时时间
     */
    String RPC_THRIFT_TIMEOUT = "rpc.thrift.timeout";

    /**
     * 日期格式:精确到天
     */
    String DATE_FORMART_DAY = "yyyyMMdd";

    /**
     * 日期格式:精确到分钟
     */
    String DATE_FORMART_MINTES = "yyyyMMddHHmm";

    /**
     * 日期时间: 精确到秒
     */
    String DATE_FORMART_SECONDS = "yyyyMMddHHmmss";


    /**
     * 用于标记三急--急加速
     */
    String  THREE_URGENT_ACC = "acc";


    /**
     * 用于标记三急--急减速
     */
    String THREE_URGENT_DECEL = "decel";


    /**
     *  用于标记三急--急转弯
     */
    String  THREE_URGENT_SHARP_TURN = "sharpTurn";

}

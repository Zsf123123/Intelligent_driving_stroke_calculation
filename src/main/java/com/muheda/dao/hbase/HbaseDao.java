package com.muheda.dao.hbase;

import com.muheda.utils.ReadProperty;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

/**
 * 操作Hbase的DAO
 * @author zhangshaofan
 *
 */
@SuppressWarnings("deprecation")
@Component
public class HbaseDao {

    private static final Logger logger = LoggerFactory.getLogger(HbaseDao.class);

    // 设备类型前缀用于区分这个是什么设备的数据
    @Value("${hbase.basicData.rowkey.pre}")
    private static  String deviceTypePre = ReadProperty.getConfigData("hbase.basicData.rowkey.pre");

    @Value("${hbase.zookeeper.quorum}")
    private  static  String zkQuorum = ReadProperty.getConfigData("hbase.zookeeper.quorum");

    @Value("${hbase.zookeeper.property.clientPort}")
    private  static  String zkPort = ReadProperty.getConfigData("hbase.zookeeper.property.clientPort");

    @Value("${hbase.master}")
    private  static  String hbaseMaster = ReadProperty.getConfigData("hbase.master");

    @Value("${zookeeper.znode.parent}")
    private  static  String zkParent = ReadProperty.getConfigData("zookeeper.znode.parent");

    @Value("${hbase.basicData.tableName}")
    private  static  String basicDataTableName = ReadProperty.getConfigData("hbase.basicData.tableName");

    private  static  Table  basicDataTable = null;

    private static Configuration conf = null;

    // hbase的连接建立
    private static Connection connection = null;



    // 初始化静态配置
    static {
        conf = HBaseConfiguration.create();//使用eclipse时必须添加这个，否则无法定位

        conf.set("hbase.zookeeper.quorum", zkQuorum);
        conf.set("hbase.zookeeper.property.clientPort", zkPort);
        conf.set("hbase.master", hbaseMaster);
        conf.set("zookeeper.znode.parent", zkParent);

        try {
            connection =  ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            logger.error("hbase 数据库连接失败");
            e.printStackTrace();
        }

        // 基础数据表的初始化
        try {
            if(basicDataTableName != null){
                basicDataTable  = connection.getTable(TableName.valueOf(basicDataTableName));
            }else {
                logger.error("基础数据表名获取失败");
            }
        } catch (IOException e) {
            logger.error("基础数据表初始化失败");
            e.printStackTrace();
        }

    }



    /*
     * 遍历查询hbase表start_rowkey到stop_rowkey之间的数据
     * @param tableName     表名
     * @param startrow  开始的rowkey
     * @param endrow    结束的rowkey
     * @throws IOException
     */
    public  List<Map<String,String>> GetSpecifiedColumn(String tableName, String family, String startrow,
                                             String endrow, List<String> params) {

        List<Map<String,String>> mapList = new ArrayList<Map<String, String>>();

        Scan scan = new Scan();
        scan.addFamily(family.getBytes());
        scan.setStartRow(startrow.getBytes());
        scan.setStopRow( (endrow + "z") .getBytes());

        ResultScanner resultScanner = null;

        try {
            resultScanner = basicDataTable.getScanner(scan);
        } catch (IOException e) {
            logger.error("获取" + tableName + "数据失败 !!");
            e.printStackTrace();
        }


        Iterator<Result> iterator = resultScanner.iterator();

        while (iterator.hasNext()){

            Result next = iterator.next();
            List<Cell> cells = next.listCells();
            Map<String, String> resultMap = new HashMap<>(params.size());

            for (Cell cell : cells ) {

                String key = new String(CellUtil.cloneQualifier(cell));

                if(key != null && params.contains(key)){
                    String value = new String(CellUtil.cloneValue(cell));
                    resultMap.putIfAbsent(key,value);
                }

            }


            if(resultMap.size() > 0){
                mapList.add(resultMap);
            }

        }


        return mapList;


    }


    /**
     * @desc  获取事件数据
     * @param tableName
     * @param family
     * @param startrow
     * @param endrow
     * @param
     * @return
     */
    public  List<Map<String,String>>  getEvenetData(String tableName, String family, String startrow, String endrow,List<String> params){




        return  null;
    }








}
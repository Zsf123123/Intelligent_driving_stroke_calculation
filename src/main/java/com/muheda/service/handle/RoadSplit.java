package com.muheda.service.handle;

import com.muheda.domain.Contans;
import com.muheda.domain.LngAndLat;
import com.muheda.domain.Roadlevel;
import com.muheda.rpc.thrift.client.Client;
import com.muheda.rpc.thrift.produce.RectDouble;
import com.muheda.rpc.thrift.produce.RoadDouble;
import com.muheda.utils.MapUtils;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class RoadSplit {

    public  Map<String,Object > splitRoad(List<Map<String,String>> list) {

        Map<String, Object> BasicIndicator = new HashMap<>();

        List<LngAndLat> lngAndLats = new LinkedList<>();

        for (Map<String,String>  map : list) {

            LngAndLat lngAndLat = new LngAndLat();

            lngAndLat.setLng(Double.parseDouble(map.get("lnt")));
            lngAndLat.setLat(Double.parseDouble(map.get("lat")));

            lngAndLats.add(lngAndLat);
        }

        /**
         *  在这些按照路段分割的行程之内。再次按照距离进行分割。以免出现一些关于设备出现的其他的问题。比如有的设备会经常发送一些经纬度为0的数据
         */
        List<List<LngAndLat>> lists = DealWithRoute.fineGrainPathSegmentation(lngAndLats);

        /**
         * 需要将切分割完之后的路段。根据判断该行驶路程是否出现拐弯的情况，再进行分路段分割，以确保每一段路是在一条路上
         **/
        List<List<LngAndLat>> routesOneWay = DealWithRoute.splitRoadByDirection(lists);

        for (List<LngAndLat>  driveLittleRoad : routesOneWay) {

            /**
             * 获取这条路的最小矩形
             */
            RectDouble rectDouble = MapUtils.minimumRectangle(driveLittleRoad);


            /**
             * 调用服务获取该行驶路段可能在哪条路段上行驶
             */
            List<RoadDouble> associatedRoads = Client.getAssociatedRoads(rectDouble);

            /**
             * 获取最相近的一条路,并获取该路的roadLevel
             *
             */
            RoadDouble roadDouble = DealWithRoute.chooseMostMactchRoad(driveLittleRoad, associatedRoads);

            /**
             * 获取道路等级对应的存储数据库表中的名字
             */
            String mapLevelName = Roadlevel.roadLevelMapping.get(roadDouble.roadLevel).get("name");

            /**
             * 算出该条路的行驶距离
             */
            Double routeDistance = DealWithRoute.getRouteDistance(driveLittleRoad);

            /**
             * 通过道路等级对驾驶里程进行累加
             */
            BasicIndicator.put(mapLevelName,routeDistance + (Double) BasicIndicator.get(mapLevelName));

            /**
             * 验证该路段是否是市区,等待外部接口实现
             */
            if("1".equals(roadDouble.innerCity)){
                BasicIndicator.put("urbanDriveLength",routeDistance + (Double) BasicIndicator.get("urbanDriveLength"));
            }

            /**
             * 计算超速次数 和此段行程的最高速，根据形式的不同的路段级别计算其是否超速 比如根据它正在行驶在高速上 时速是 120km/h 如果此时它行驶 130km/s 此时就属于超速 + 1
             */
            Map<String, Object> overSpeedTimesAndHighestSpeed = SafetyDrivingCheckWarp.getOverSpeedTimesAndHighestSpeed(roadDouble.roadLevel, driveLittleRoad);


            /**
             * 超速次数
             */
            Integer count = (Integer) overSpeedTimesAndHighestSpeed.get("count");
            BasicIndicator.put("overSpeedTimes", (Integer)BasicIndicator.get("overSpeedTimes") + count);

            /**
             * 最高速更新
             */
            Double highestSpeed = (Double)overSpeedTimesAndHighestSpeed.get("highestSpeed");
            Double tempHighestSpeed = (Double)BasicIndicator.get("highestSpeed");

            if(tempHighestSpeed < highestSpeed){
                BasicIndicator.put("highestSpeed",highestSpeed);
            }



            /**
             * 验证是否是夜间行驶 返回的是另一个夜间行驶的比例
             */
            double  proportion = DealWithRoute.getTimeDriveAtNight(driveLittleRoad);
            double  nightDriveLength = routeDistance * proportion;
            BasicIndicator.put("NightDriveLength", (Double)BasicIndicator.get("NightDriveLength") + nightDriveLength);


            /***
             *  计算行车三急
             */
            Map<String, Integer> threeUrgent = SafetyDrivingCheckWarp.getThreeUrgent(driveLittleRoad);

            BasicIndicator.put("DeviceSpeedUpTimes", (Double)BasicIndicator.get("DeviceSpeedUpTimes") + threeUrgent.get(Contans.THREE_URGENT_ACC));
            BasicIndicator.put("DeviceSpeedDownTimes", (Double)BasicIndicator.get("DeviceSpeedDownTimes") + threeUrgent.get(Contans.THREE_URGENT_DECEL));
            BasicIndicator.put("DeviceSharpTurnTimes", (Double)BasicIndicator.get("DeviceSharpTurnTimes") + threeUrgent.get(Contans.THREE_URGENT_SHARP_TURN));



        }


        return BasicIndicator;

    }






}

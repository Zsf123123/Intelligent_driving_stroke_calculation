package com.muheda.service.handle;

import com.muheda.domain.Contans;
import com.muheda.domain.LngAndLat;
import com.muheda.domain.NightDriveTime;
import com.muheda.rpc.thrift.produce.PointDouble;
import com.muheda.rpc.thrift.produce.RoadDouble;
import com.muheda.utils.DateUtils;
import com.muheda.utils.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * @desc 根据设备号和时间查询该设备的相关数据
 */

public class DealWithRoute {

    private static Logger logger = LoggerFactory.getLogger(DealWithRoute.class);


    /**
     * @desc 在按照时间分割完成之后的路程的基础之上更加细粒度的路程分割，排除一些异常的设备发送的特殊数据
     * Fine-grained path segmentation
     */
    public static List<List<LngAndLat>> fineGrainPathSegmentation(List<LngAndLat> list) {

        LinkedList<List<LngAndLat>> resultLists = new LinkedList<>();

        List<List<LngAndLat>> lists = dealWithRoutereventException(list);

        return resultLists;
    }


    /**
     * @return
     * @desc 检测行程是否需要进行路程的分割
     */
    public static List<List<LngAndLat>> dealWithRoutereventException(List<LngAndLat> list) {


        List<List<LngAndLat>> resultLists = new LinkedList<List<LngAndLat>>();

        int start = 0;

        //从第二个点开始进行验证
        for (int i = 1; i < list.size(); i++) {


            //如果验证该点有问题
            if (!verifLegalPoint(list.get(i - 1), list.get(i))) {

                resultLists.add(list.subList(start, i));
                start = i;
            }
        }

        //在遍历之后查看是否还存在没有最后一段行程没有放入返回的集合中
        if (start < list.size() - 1) {

            resultLists.add(list.subList(start, list.size() - 1));

        }

        return resultLists;
    }

    /**
     * @param pre     当前点的前一个点
     * @param current 当前的点
     * @return true 表示此点没有问题
     * @desc 验证一个点是不是合法的点
     */
    public static boolean verifLegalPoint(LngAndLat pre, LngAndLat current) {

        //如果是某个点的经纬度之一为0.0 或者该点距离上一个点的距离大于一个临界值 1000m （暂定，后期写入配置文件中）
        return current.getLng() != 0.0 && current.getLat() != 0.0 && !(MapUtils.getDistance(current.getLng(), current.getLat(), pre.getLng(), pre.getLat()) > 1000);

    }

    /**
     * @desc 将一段路按照向量角度分割出不同的路
     */
    public static List<List<LngAndLat>> makeOneRoadToSomeRoad(List<LngAndLat> list) {


        List<List<LngAndLat>> resultList = new LinkedList<>();

        // 前一个向量
        LngAndLat preVector = new LngAndLat();

        //后一个向量
        LngAndLat nextVector = new LngAndLat();

        // 截取的开始的index
        int from = 0;

        // 遍历的时候从第二个点开始。到倒数第二个点作为结束

        for (int i = 1; i < list.size() - 1; i++) {

            LngAndLat pre = list.get(i - 1);

            LngAndLat current = list.get(i);

            LngAndLat next = list.get(i + 1);


            double v = current.getLng() - pre.getLng();


            preVector.setLng(v);
            preVector.setLat(current.getLat() - pre.getLat());


            nextVector.setLng(next.getLng() - current.getLng());
            nextVector.setLat(next.getLat() - current.getLat());


            double degree = CalculaeVectorAngel(preVector.getLng(), preVector.getLat(), nextVector.getLng(), nextVector.getLat());


            // 如果2个向量之间的夹角大于30度。 那么将这2条路进行分开
            if (degree > 30.0) {
                resultList.add(list.subList(from, i));
                from = i;
            }


        }


        //在根据方向将路段分割完之后，判断是否还有最后一段没有添加到分割路段之后
        if (from != (list.size() - 1)) {

            resultList.add(list.subList(from, list.size() - 1));
        }


        return resultList;

    }

    /**
     * @desc Vector angle  向量的角度计算方法，计算2个二维向量的夹角
     */
    public static double CalculaeVectorAngel(double point1X, double point1Y, double point2X, double point2Y) {


        double x = point1X * point2X + point1Y * point2Y;
        double y = Math.sqrt(point1X * point1X + point1Y * point1Y) * Math.sqrt(point2X * point2X + point2Y * point2Y);

        double num = x / y;

        double degree = Math.acos(num) * (180 / 3.14159265);

        return degree;
    }

    /**
     * @param lists 之前以前分割好的路段
     * @return 理想状态下返回的每一段路都是属于某一条路的
     * @desc 目标：如果相邻的2个向量之间的角度大于30度。我们将会将路段分割开来
     */
    public static List<List<LngAndLat>> splitRoadByDirection(List<List<LngAndLat>> lists) {

        List<List<LngAndLat>> resultLists = new LinkedList<>();

        for (List<LngAndLat> list : lists) {

            List<List<LngAndLat>> lists1 = makeOneRoadToSomeRoad(list);

            for (List<LngAndLat> list1 : lists1) {
                resultLists.add(list1);
            }
        }

        return resultLists;
    }


    /**
     * @desc   在几条路中选择可能性最大的那条路
     * @return 返回该路的所属的类型,等待后续优化
     */
    public static RoadDouble chooseMostMactchRoad(List<LngAndLat> road, List<RoadDouble> toBeDetermineds){

        RoadDouble finalRoad = null;
        Double minDistance = 0.0;

        boolean first = true;

        for ( LngAndLat lngAndLat : road) {

            for (RoadDouble roadDouble : toBeDetermineds) {

                List<PointDouble> pts = roadDouble.getPts();

                for (PointDouble point : pts) {

                    double dx = point.getDX();
                    double dy = point.getDY();

                    double distance = MapUtils.getDistance(dx, dy, lngAndLat.getLng(), lngAndLat.getLat());

                    if(first = true){
                        minDistance = distance;
                        first = false;
                        finalRoad = roadDouble;
                    }

                    if(distance < minDistance){
                        minDistance = distance;
                        finalRoad = roadDouble;
                    }
                }

            }

        }

        return finalRoad;
    }


    /**
     * @desc 计算一个行程的点的里程
     *
     */
    public static Double getRouteDistance(List<LngAndLat> road){

        Double distance = 0.0;

        for (int i = 0; i < road.size() -1 ; i++) {

            LngAndLat pre = road.get(i);
            LngAndLat next = road.get(i + 1);
            distance += MapUtils.getDistance(pre.getLng(), pre.getLat(), next.getLng(), next.getLat());
        }
        return distance;

    }


    /**
     * @desc 判断是否是在夜间行驶，如果的是夜间驾驶时间的比例
     *
     */
    public static double getTimeDriveAtNight(List<LngAndLat> list){

        double proportion = 0;

        if(list == null || list.size() ==0){
            return proportion;
        }

        /**
         * 获取第一个点和最后一个点
         */
        Date firstTime = list.get(0).getDate();
        Date lastTime = list.get(list.size() - 1).getDate();

        //总行程占用多少秒
        int sumSeconds = DateUtils.getDiffDate(firstTime, lastTime, Calendar.SECOND);

        if(sumSeconds <= 0){
            return  proportion;
        }

        //确定开始和结束之间经历的时间段(是否大于24小时)
        int diffDay = DateUtils.getDiffDate(firstTime, lastTime, Calendar.DAY_OF_MONTH);

        // 行程的起始是在同一天
        if(diffDay == 0){
            proportion =   countCurrentNightTime(firstTime,lastTime);

            return proportion / sumSeconds;
        }

        if(diffDay > 0){

            long nightTime = 0;

            Calendar cal=Calendar.getInstance();
            cal.setTime(firstTime);

            //确定2个时间之间相隔多少天
            proportion += (diffDay - 1) * 12 * 3600;

            //取出开始的那一天的行驶时间，从开始到这一天的结束
            String first = DateUtils.dateToStrLong(Contans.DATE_FORMART_SECONDS,firstTime).substring(0, 8) + "235959";

            Date firstTimeEnd = DateUtils.timeFormat(Contans.DATE_FORMART_SECONDS, first);

            nightTime += countCurrentNightTime(firstTime, firstTimeEnd);

            //取出最后一天的行驶时间，从这一天开始到我们行程的结束作为一个行程
            String last = DateUtils.dateToStrLong(Contans.DATE_FORMART_SECONDS,lastTime).substring(0, 8);

            Date lastTimeEnd = DateUtils.timeFormat(Contans.DATE_FORMART_DAY, last);

            nightTime += countCurrentNightTime(lastTime, lastTimeEnd);

            proportion = (double) nightTime / (double) sumSeconds;

            return proportion ;

        }


    return proportion;


    }


    /**
     *
     * @param start 开始的时间
     * @param end   结束的时间
     * @return
     */
    public static long countCurrentNightTime(Date start, Date end){

        long sumSeconds = 0;

        Calendar cal=Calendar.getInstance();
        cal.setTime(start);

        //获取当天所在月份的夜间行驶时间
        NightDriveTime instance = NightDriveTime.getInstance(String.valueOf(cal.get(Calendar.MONTH)));

        //夜间行驶的开始和结束
        String startTime = instance.getStartTime();
        String endTime = instance.getEndTime();

        //获取行程开始，结束的时间 精确到秒
        String today = DateUtils.dateToStrLong(Contans.DATE_FORMART_SECONDS,start).substring(0, 10);

        //结束时间在早上 作为一天开始的分界线
        String currentStart = today + endTime;
        String currentEnd = today + startTime;

        //这里的开始指的是白天的开始，与夜间刚好相反 方便后面计算
        long startTimeStamp = DateUtils.timeFormat(Contans.DATE_FORMART_DAY,currentStart).getTime();
        long endTimeStamp = DateUtils.timeFormat("yyyyMMddHHmmss",currentEnd).getTime();

        long currentStartStamp = start.getTime();
        long currentEndStamp = end.getTime();

        if(startTimeStamp < currentStartStamp && currentEndStamp < endTimeStamp){
            return sumSeconds;
        }

        // 表示全部时间都属于夜间行程
        if (currentEndStamp < startTimeStamp || currentStartStamp > endTimeStamp) {
            return  (currentEndStamp - currentStartStamp) / 1000;
        }

        //如果行程在白天和黑夜之间
        if(startTimeStamp > currentStartStamp ){
            long firstSegment = startTimeStamp - currentStartStamp;
             sumSeconds += firstSegment;
        }

        if(currentEndStamp > endTimeStamp){
            long secondSegment  = currentEndStamp - endTimeStamp;
            sumSeconds += secondSegment;
        }


        return  sumSeconds / 1000;

    }



}

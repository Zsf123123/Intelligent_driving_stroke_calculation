import com.muheda.domain.Contans;
import com.muheda.domain.DriveData;
import com.muheda.domain.LngAndLat;
import com.muheda.service.handle.SafetyDrivingCheck;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SafetyDrivingCheckWarp {


   // 将设定好的急加速和急减速的界定标准
   private static double[] maxAcceleration = {1.11, 1.38, 1.67};
   private static double[] maxSharpTurn    = {2.78, 2.22, 1.67};
   private static double[] maxDecelation   = {-2.78, 2.22, 1.67};

   private static SafetyDrivingCheck safetyDrivingCheck = new SafetyDrivingCheck();

   static {
        safetyDrivingCheck.setAcceleration(maxAcceleration);
        safetyDrivingCheck.setSharpTurn(maxSharpTurn);
        safetyDrivingCheck.setDecelration(maxDecelation);
    }


    /**
     * @desc 通过传入坐标集合和时间的集合，返回所有发生三急时间的点的坐标，时间和其他相关的信息
     *
     */
    public static Map<String, Integer> getThreeUrgent(List<LngAndLat> list) {

        Map<String, Integer> map = new HashMap<>();

        int size = list.size();

        double lon [] = new  double[size];
        double lat [] = new  double[size];
        long time  [] = new  long [size];

        for (int i = 0; i < size; i++) {
            LngAndLat lngAndLat = list.get(i);
            lon [i] = lngAndLat.getLng();
            lat [i] = lngAndLat.getLat();

            long time1 = lngAndLat.getDate().getTime();
                if(time1 != 0){
                    time[i] =  time1/ 1000;
                }

        }

        SafetyDrivingCheck safetyDrivingCheck = new SafetyDrivingCheck();
        DriveData accDriveData = safetyDrivingCheck.getSpeedCheck(lon, lat, time);
        DriveData sharpTurnData = safetyDrivingCheck.getSharpTurnCheck(lon, lat, time);
        List<Double> values = accDriveData.getCheckValue();

        int acc = 0;
        int decel = 0;

        for (Double value : values) {
            if(value > 0){
                acc ++;
                continue;
            }
            decel ++;
        }


        //急加速和急转弯
        map.put(Contans.DATE_FORMART_DAY,acc);
        map.put(Contans.THREE_URGENT_DECEL,decel);

        //急转弯
        map.put(Contans.THREE_URGENT_SHARP_TURN, sharpTurnData.getCheckValue().size());

        return map;
    }

}

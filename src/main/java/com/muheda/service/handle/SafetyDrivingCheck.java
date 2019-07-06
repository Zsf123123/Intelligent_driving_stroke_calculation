package com.muheda.service.handle;

import com.muheda.domain.DriveData;

/*
    安全驾驶校验，三急判定
 */
public class SafetyDrivingCheck {

    // 数据依次递减
    private double[] maxAcceleration;
    private double[] maxSharpTurn;
    private double[] maxDecelerattion;


    // 数据单位必须为：米/秒^2,m/s2 或m·s-2（米每二次方秒）
    public SafetyDrivingCheck setAcceleration(double[] acc){
        this.maxAcceleration = null;
        this.maxAcceleration = new double[acc.length];
        for (int i=0;i<acc.length;++i){
            this.maxAcceleration[i] = acc[i];
        }

        return this;
    }


    public double[] getMaxAcceleration() {
        return maxAcceleration;
    }


    // 数据单位必须为：米/秒^2,m/s2 或m·s-2（米每二次方秒）
    public SafetyDrivingCheck setDecelration(double[] decel){
        this.maxDecelerattion = null;
        this.maxDecelerattion = new double[decel.length];
        for (int i=0;i<decel.length;++i){
            this.maxDecelerattion[i] = decel[i];
        }

        return this;
    }

    public double[] getMaxDecelerattion() {
        return maxDecelerattion;
    }

    // 数据单位必须为：弧度/秒,rad/s 或rad·s-1（弧度每秒）
    public SafetyDrivingCheck setSharpTurn(double[] sharp){
        this.maxSharpTurn = null;
        this.maxSharpTurn = new double[sharp.length];
        for (int i=0;i<sharp.length;++i){
            this.maxSharpTurn[i] = sharp[i];
        }

        return this;
    }


    public double[] getMaxSharpTurn() {
        return maxSharpTurn;
    }

    public SafetyDrivingCheck(){

    }

    // 参数为三位数据：经纬度和时间，其中经纬度必须精确到米
    // lon 对应的经度
    // lat 对应的维度
    // timre 对应的时间，单位为：秒，元素可以为0，当为0时，表示当前打点时间忽略、缺失或异常，程序处理时将自动跳过
    public DriveData getSpeedCheck(double[] lon, double[] lat, long[] time){
        if(!checkData(lon, lat, time)){
            return null;
        }

        long preTime = 0;

        double s = 0;
        double v = 0;
        double delte = 0;
        double a = 0;
        int index = -1;

        DriveData driveData = new DriveData();

        for (int i=0;i<time.length;++i){
            if(time[i] != 0){
                if(preTime == 0){
                    preTime = time[i];
                    continue;
                }

                s += dist(lon[i-1], lat[i-1], lon[i], lat[i]);
                delte = s/(time[i] - preTime);

                if(v > 0){
                    a = (delte - v)/(time[i]-preTime);
                    // 急加速与急减速
                    if(a > 0){
                        index = getAccIndex(a);
                    }else {
                        index = getDecelIndex(a);
                    }
                    if(index >= 0){
                        //加速度a的具体的数值是否大于0代表了此时是出现了急加速还是急减速
                        driveData.addData(a, time[i], index);
                    }
                }

                preTime = time[i];

                v = delte;
                s = 0;
            }
            else{

                //如果此刻的时间为空,则直接将此处的路程加上，使用下一个点作为
                if(preTime != 0){
                    s += dist(lon[i-1], lat[i-1], lon[i], lat[i]);
                }
            }
        }

        return driveData;
    }

    // 参数为三位数据：经纬度和时间
    // lon 对应的经度
    // lat 对应的维度
    // timre 对应的时间，单位为：秒，元素可以为0，当为0时，表示当前打点时间忽略、缺失或异常，程序处理时将自动跳过
    public DriveData getSharpTurnCheck(double[] lon, double[] lat, long[] time){
        if(!checkData(lon, lat, time)){
            return null;
        }

        long preTime = 0;

        double rad = 0;
        double delte = 0;
        int type = -1;

        DriveData driveData = new DriveData();

        for (int i=0;i<time.length;++i){
            if(time[i] != 0){
                if(i < 2){
                    preTime = time[i];
                    continue;
                }

                rad += rad(lon[i-1] - lon[i-2], lat[i-1] - lat[i-2], lon[i] - lon[i-1], lat[i] - lat[i-1]);

                delte = rad/(time[i] - preTime);

                type = getSharpTurnIndex(delte);

                if(type >= 0){
                    driveData.addData(delte, time[i], type);
                }

                preTime = time[i];

                rad = 0;
            }
            else{
                if(preTime != 0){
                    if(i >= 2){
                        rad += rad(lon[i-1] - lon[i-2], lat[i-1] - lat[i-2], lon[i] - lon[i-1], lat[i] - lat[i-1]);
                    }
                }
            }
        }

        return driveData;
    }

    private int getAccIndex(double d){
        d = Math.abs(d);

        for (int i=0;i<maxAcceleration.length;++i){
            if(d >= maxAcceleration[i]){
                return i;
            }
        }

        return -1;
    }

    private boolean checkData(double[] lon, double[] lat, long[] time){
        if(lon == null || lat == null || time == null){
            return false;
        }

        if(lon.length != lat.length){
            return false;
        }

        if(lon.length != time.length){
            return false;
        }

        if(time.length <= 2){
            return false;
        }

        return true;
    }


    private int getDecelIndex(double d){
        d = Math.abs(d);

        for (int i=0;i<maxDecelerattion.length;++i){
            if(d >= maxDecelerattion[i]){
                return i;
            }
        }

        return -1;
    }



    private int getSharpTurnIndex(double d){
        d = Math.abs(d);

        for (int i=0;i<maxSharpTurn.length;++i){
            if(d >= maxSharpTurn[i]){
                return i;
            }
        }

        return -1;
    }

    // 两个点的坐标
    private double dist(double lon1, double lat1, double lon2, double lat2){
        double s = 0;

        s += (lon2 - lon1)*(lon2 - lon1);
        s += (lat2 - lat1)*(lat2 - lat1);

        return Math.sqrt(s);
    }

    // 两个向量的坐标
    private double rad(double lon1, double lat1, double lon2, double lat2){
        double vecFZ = 0;
        double vecFM = 0;

        vecFZ = lon1 * lon2 + lat1 * lat2;
        vecFM = Math.sqrt(lon1*lon1 + lat1*lat1)*Math.sqrt(lon2*lon2 + lat2*lat2);

        if(vecFM == 0){
            return 0;
        }

        return Math.acos(vecFZ/vecFM);
    }
}




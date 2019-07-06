import com.muheda.domain.LngAndLat;
import com.muheda.service.handle.DealWithRoute;
import com.muheda.utils.DateUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class funtionTest {


    @Test
    public void TestDifferentMonthNightTime(){



    }


    public void Test1(){


        List<LngAndLat> lngAndLats = new ArrayList<>();


        Date start = DateUtils.timeFormat("yyyyMMddHHmmsss", "20190629000000");
        LngAndLat lngAndLat = new LngAndLat();
        lngAndLat.setLng(123.1);
        lngAndLat.setLat(23.23);
        lngAndLat.setDate(start);

        Date end = DateUtils.timeFormat("yyyyMMddHHmmsss", "20190701000000");

        LngAndLat lngAndLat1 = new LngAndLat();
        lngAndLat1.setLng(123.2);
        lngAndLat1.setLat(23.4);
        lngAndLat1.setDate(end);

        lngAndLats.add(lngAndLat);
        lngAndLats.add(lngAndLat1);


        double timeDriveAtNight = DealWithRoute.getTimeDriveAtNight(lngAndLats);

        System.out.println(timeDriveAtNight);

    }


}

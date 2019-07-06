import com.muheda.utils.ReadProperty;

import java.util.Map;

public class PropertiesTest {

    public static void main(String[] args) {


    /*    Map<String, Object> basicConfig = ReadProperty.getBasicConfig();
        String basicfamily = (String) basicConfig.get("family");

        List<String> list = (List<String>) basicConfig.get("need");

        System.out.println(basicfamily);
        System.out.println(list);
*/


        Map<String, Map<String, String>> eventConfig = ReadProperty.getEventConfig();

        for (Map.Entry<String, Map<String, String>> entry : eventConfig.entrySet()) {

            String family = entry.getKey();

            Map<String, String> value = entry.getValue();

            for (Map.Entry<String,String>  entry1 : value.entrySet()) {

                System.out.println(entry1.getKey());
                System.out.println(entry1.getValue());

            }

        }





    }

}

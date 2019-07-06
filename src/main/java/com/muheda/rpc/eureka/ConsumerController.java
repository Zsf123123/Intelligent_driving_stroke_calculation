package com.muheda.rpc.eureka;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;



public class ConsumerController {

    private String  service4s =  "http://service-4s-client";

    private String  serviceIot =  "http://service-iot";

    private String  basicService = "http://SERVICE-DEVICE-BASIC-DATA";

    private  String poiServcie = "http://poiSearch";

    @Autowired
    RestTemplate restTemplate = new RestTemplate();



    public String  poiTest(){

        LinkedMultiValueMap <String,Object> mapRoute = new LinkedMultiValueMap<String,Object> ();

        mapRoute.add("lng",115.743741);
        mapRoute.add("lat", 39.983294);

        ResponseEntity<String> responseEntity = restTemplate
                .postForEntity(poiServcie + "/test/v2", mapRoute,String.class);

        return responseEntity.toString();

    }




}







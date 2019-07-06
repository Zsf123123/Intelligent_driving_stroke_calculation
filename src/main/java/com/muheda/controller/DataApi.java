package com.muheda.controller;

import com.muheda.domain.IntelligentDriveTravel;
import com.muheda.rpc.eureka.ConsumerController;
import com.muheda.service.DataApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @desc 用户页面管理相关接口
 *
 */
@Controller
@RequestMapping("/dataApi")
public class DataApi {

    @Autowired
    DataApiService dataApiService;


    @ResponseBody
    @RequestMapping("/consume")
    public String test1(){

        ConsumerController consumerController = new ConsumerController();
        return    consumerController.poiTest();
    }



    @GetMapping("/queryDetailInterface")
    @CrossOrigin(origins = "*")
    @ResponseBody
    public void queryDetailInterface() {


        IntelligentDriveTravel intelligentDriveTravel = new IntelligentDriveTravel();
        intelligentDriveTravel.setDriveTime(10);
        intelligentDriveTravel.setDeviceId("123");
        intelligentDriveTravel.setPrimarykey("1234");

        dataApiService.insertVehicleJourney(intelligentDriveTravel);

    }

}

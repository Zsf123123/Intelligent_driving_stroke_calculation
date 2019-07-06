package com.muheda.service.impl;

import com.muheda.dao.mysql.mapper.DataApiMapper;
import com.muheda.domain.IntelligentDriveTravel;
import com.muheda.service.DataApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DataApiServiceImpl implements DataApiService {


    @Autowired
    DataApiMapper dataApiMapper;

    @Override
    public List<String> queryAllProject() {

        return dataApiMapper.queryAllProject();

    }

    @Override
    public void insertVehicleJourney(IntelligentDriveTravel intelligentDriveTravel) {

        dataApiMapper.insertVehicleJourney(intelligentDriveTravel);

    }


}

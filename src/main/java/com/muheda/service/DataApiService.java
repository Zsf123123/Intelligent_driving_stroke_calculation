package com.muheda.service;

import com.muheda.domain.IntelligentDriveTravel;

import java.util.List;

public interface DataApiService {

    public List<String> queryAllProject();

    void  insertVehicleJourney(IntelligentDriveTravel intelligentDriveTravel);

}





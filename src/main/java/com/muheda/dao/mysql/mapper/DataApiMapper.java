package com.muheda.dao.mysql.mapper;

import com.muheda.domain.IntelligentDriveTravel;

import java.util.List;

public interface DataApiMapper {

    List<String> queryAllProject();

    void insertVehicleJourney(IntelligentDriveTravel intelligentDriveTravel);
}

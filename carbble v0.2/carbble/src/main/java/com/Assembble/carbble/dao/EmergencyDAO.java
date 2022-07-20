package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.EmergencyDTO;
import com.Assembble.carbble.dto.EmergencyPutDTO;

import java.sql.Date;
import java.util.List;

public interface EmergencyDAO {
    int insertEmergency(EmergencyDTO dto);

    List<EmergencyDTO> selectEmergency(Date startdate, Date enddate);

    int updateEmergency(int emergency_id, EmergencyPutDTO dto);
}

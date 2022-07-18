package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.CarDTO;
import com.Assembble.carbble.dto.CarPutDAO;

import java.util.List;

public interface CarDAO {

    List<CarDTO> select();

    CarDTO select(int car_id);

     int updateUseStatus(int car_id, int use_status);


    int updatemileage(int car_id, CarPutDAO cdto);
}

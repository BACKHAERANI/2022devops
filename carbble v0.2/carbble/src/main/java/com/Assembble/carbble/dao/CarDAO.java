package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.CarDTO;
import com.Assembble.carbble.dto.UserDTO;

import java.util.List;

public interface CarDAO {

    List<CarDTO> select();

    CarDTO select(int car_id);

     int update(int car_id);


    int updatemileage(int car_id, CarPutDTO cdto);
}

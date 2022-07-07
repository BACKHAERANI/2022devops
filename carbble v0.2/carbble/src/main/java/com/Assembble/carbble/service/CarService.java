package com.Assembble.carbble.service;

import com.Assembble.carbble.dao.CarPutDTO;
import com.Assembble.carbble.dto.CarDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> getCar();

    CarDTO getCar(int car_id);

    int putUseStatus(int car_id);

    int putCarmileage(int car_id, CarPutDTO cdto);
}

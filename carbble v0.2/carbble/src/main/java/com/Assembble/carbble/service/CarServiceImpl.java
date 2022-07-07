package com.Assembble.carbble.service;

import com.Assembble.carbble.dao.CarDAOImpl;
import com.Assembble.carbble.dao.CarPutDTO;
import com.Assembble.carbble.dto.CarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarServiceImpl implements  CarService{

    @Autowired
    CarDAOImpl car;

    @Override
    public List<CarDTO> getCar()
    { return car.select();}


    @Override
    public int putUseStatus(int car_id)
    {return car.update(car_id);}


    @Override
    public CarDTO getCar(int car_id) {
        return car.select(car_id);
    }

    @Override
    public int putCarmileage(int car_id, CarPutDTO cdto){
        return car.updatemileage(car_id, cdto);
    }
}

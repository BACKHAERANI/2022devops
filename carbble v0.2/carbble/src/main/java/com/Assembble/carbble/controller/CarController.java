package com.Assembble.carbble.controller;

import com.Assembble.carbble.dto.CarDTO;
import com.Assembble.carbble.service.CarServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;


@EnableSwagger2
@Validated
@Slf4j
@RestController
public class CarController {

    @Autowired
    CarServiceImpl car;



    @RequestMapping(value = "/cars" , method = RequestMethod.GET)
    public ResponseEntity<List<CarDTO>> getCarList(){

        List<CarDTO> carDTO = car.getCar();

        if (carDTO.isEmpty()) {
            log.info("select car 400:[{}]", car.getCar());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {
            log.info("select car 200:[{}]", car.getCar());
            return new ResponseEntity<>(carDTO, HttpStatus.OK);
        }

    }

    @RequestMapping(value = "/cars/{carId}", method = RequestMethod.PUT)
    public ResponseEntity<String> putUseStatus(@PathVariable("carId") int car_id) {
        System.out.println("controll : ");
        System.out.println(car_id);

        if (car.putUseStatus(car_id) >0) {
            log.info("put car 200:[{}]", "SUCCESS");
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            log.error("put car 404:[{}]", "FAIL");
            String strErrorBody = "{\"reason\":\"id를 찾을 수 없습니다.\"}";
            return new ResponseEntity<>(strErrorBody, HttpStatus.NOT_FOUND);
        }

    }


}

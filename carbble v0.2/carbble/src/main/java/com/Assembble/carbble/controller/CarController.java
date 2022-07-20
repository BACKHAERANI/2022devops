package com.Assembble.carbble.controller;

import com.Assembble.carbble.dto.CarDTO;
import com.Assembble.carbble.service.DatabaseServiceImpl;
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
    DatabaseServiceImpl car;


    @RequestMapping(value = "/cars" , method = RequestMethod.GET)
    public ResponseEntity<List<CarDTO>> getCarList()
    {
        List<CarDTO> carDTO = car.getCar();

        if (carDTO.isEmpty()) {
            log.info("select car Empty:[{}]", carDTO);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            //list length
            log.info("select getcar count:[{}]", carDTO.size());
            return new ResponseEntity<>(carDTO, HttpStatus.OK);
        }
    }



    @RequestMapping(value = "/cars/{carId}", method = RequestMethod.PUT)
    public ResponseEntity<String> modifyUseStatus(@PathVariable("carId") int car_id, int use_status)
    {

        //car_id, useStatus  log
        log.info("put car car_id:[{}]", car_id);
        log.info("put car use_status:[{}]", use_status);

        if (car.putUseStatus(car_id, use_status) >0) {
            log.info("put car 200:[{}]", "SUCCESS");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            log.error("put car 404:[{}]", "FAIL");
            String strErrorBody = "{\"reason\":\"id를 찾을 수 없습니다.\"}";
            return new ResponseEntity<>(strErrorBody, HttpStatus.NOT_FOUND);
        }

    }


}

package com.Assembble.carbble.controller;


import com.Assembble.carbble.dao.CarPutDTO;
import com.Assembble.carbble.dto.*;
import com.Assembble.carbble.service.CarServiceImpl;
import com.Assembble.carbble.service.ReservationServiceImpl;
import com.Assembble.carbble.service.ReturnServiceImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@EnableSwagger2
@Validated
@Slf4j
@RestController
public class ReturnController {


    @Autowired
    ReturnServiceImpl returnService;

    @Autowired
    CarServiceImpl carService;
    @Autowired
    ReservationServiceImpl reservationService;



    @RequestMapping(value = "/returns", method = RequestMethod.GET)
    public ResponseEntity<List<ReturnDTO>> getReturnList() {

        List<ReturnDTO> dto = returnService.getReturn();

        if (dto.isEmpty()) {
            log.info("select return 400:[{}]", returnService.getReturn());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {
            log.info("select return 200:[{}]", returnService.getReturn());
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }

    }


    @RequestMapping(value = "/returns/{reservationId}", method = RequestMethod.POST)
    public ResponseEntity<String> postReturn(@PathVariable int reservationId ,@Valid @RequestBody IReturnDTO dto) {

        int car_id = dto.getCar_id();
        CarDTO carDto = carService.getCar(car_id);


        ReturnDTO returnDto = new ReturnDTO();

        returnDto.setReservationId(reservationId);
        returnDto.setDestination(dto.getDestination());
        returnDto.setParking(dto.getParking());
        returnDto.setRefuelingAmount(dto.getRefueling_amount());
        returnDto.setDamage(dto.getDamage());
        returnDto.setLighting(dto.getLighting());
        returnDto.setDashboard(dto.getDashboard());
        returnDto.setBesides(dto.getBesides());
        //마일리지
        returnDto.setMileage(dto.getMileage());


        if (carDto.getCarMileage() < dto.getMileage()) {
            int distance = dto.getMileage() - carDto.getCarMileage();
            returnDto.setDistance(distance);
        } else {
            log.error("return distance :[{}]", "거리입력불가");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        //car_mileage = return.mileage
        CarPutDTO carPutDTO = new CarPutDTO();
        carPutDTO.setCarMileage(dto.getMileage());


        if (carService.putCarmileage(car_id, carPutDTO) > 0) {
            log.info("car carmileage 200:[{}]", "SUCCESS");

        } else {
            log.error("car carmileage 400:[{}]", "누적마일리지 수정 실패");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


        if (returnService.postReturn(returnDto) > 0) {

            log.info("post return 201:[{}]", "SUCCESS");
            return new ResponseEntity<>(HttpStatus.CREATED);

        } else {
            log.error("post return 400:[{}]", "FAIL");
            String strErrorBody = "{\"reason\":\"잘못된 요청입니다.\"}";
            return new ResponseEntity<>(strErrorBody, HttpStatus.BAD_REQUEST);
        }


    }
}

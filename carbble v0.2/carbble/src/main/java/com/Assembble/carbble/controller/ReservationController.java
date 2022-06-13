package com.Assembble.carbble.controller;

import com.Assembble.carbble.dto.ReservationDTO;
import com.Assembble.carbble.service.ReservationServiceImpl;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@EnableSwagger2
@Slf4j
public class ReservationController {

    @Autowired
    ReservationServiceImpl service;

    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public List<ReservationDTO> getReservation(){

        return service.getReservation();

        }


//
//    @RequestMapping(value = "/reservations", method = RequestMethod.PUT)
//    public Map<String, Object> Reservation(){
//
//
//
//    }
//

    @RequestMapping(value = "/reservations", method = RequestMethod.POST)
    public Map<String, Object> addReservation( @RequestBody ReservationDTO dto) {
        Map<String, Object> response = new HashMap<>();

        if(service.addReservation(dto) > 0){
            response.put("result", "SUCCESS");
        } else {
            response.put("result", "FAIL");
        }
        return response;
    }



    @RequestMapping(value = "/reservations/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> removeReservation(@PathVariable("id") Integer id) {
        Map<String, Object> response = new HashMap<>();


        //1은성공 0는 실패

        if(service.removeReservation(id) > 0) {
            response.put("result", "SUCCESS");
        } else {
            response.put("result", "FAIL");
            response.put("reason", "id를 확인해주세요.");
        }

        return response;
    }




    }
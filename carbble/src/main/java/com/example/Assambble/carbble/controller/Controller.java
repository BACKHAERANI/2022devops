package com.example.Assambble.carbble.controller;

import com.example.Assambble.carbble.domain.Reservation;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
public class Controller {

    ResponseEntity<?> entity = null;

    @ApiOperation(value = "예약", notes="예약등록")
    @RequestMapping(value = "/reserv", method = RequestMethod.GET)
    public ResponseEntity<?> reservation(String username){
        Reservation reservation = new Reservation();
        reservation.setUsername(username);
        entity = new ResponseEntity<Reservation>(reservation, HttpStatus.OK);
                return entity;

    }

}

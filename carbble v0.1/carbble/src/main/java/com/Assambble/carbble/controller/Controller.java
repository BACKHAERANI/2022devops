package com.Assambble.carbble.controller;


import com.Assambble.carbble.dto.PutDTO;
import com.Assambble.carbble.dto.ReservationDTO;
import com.Assambble.carbble.model.entity.Reservation;
import com.Assambble.carbble.service.ReservationService;

import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@EnableSwagger2
@RequiredArgsConstructor
public class Controller {


    private final ReservationService reservationService;


    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
    public  List<Reservation> findAll(){
        return reservationService.findAll();
    }


    @RequestMapping(value = "/reservations/{id}", method = RequestMethod.GET)
    public Map<String, Object> findById(@PathVariable("id") Integer id) {
        Map<String, Object> response = new HashMap<>();

        Optional<Reservation> reserv = reservationService.findById(id);
        if(reserv.isPresent()) {
            response.put("result", "SUCCESS");
            response.put("reservation", reserv.get());
        } else {
            response.put("result", "FAIL");
            response.put("reason", "예약없음");
        }

        return response;

    }


    @RequestMapping(value = "/reservations", method = RequestMethod.POST)
    public Map<String, Object> addReservation( @RequestBody ReservationDTO dto) {
        Map<String, Object> response = new HashMap<>();

        Reservation reservation = reservationService.save(dto);
        if(dto.getUsername() !=null) {
            response.put("result", "SUCCESS");
            response.put("user", dto);
        } else {
            response.put("result", "FAIL");
            response.put("reason", "예약불가능");
        }

        return response;
    }

    @RequestMapping(value = "/reservations/{id}", method = RequestMethod.PUT)
    public Map<String, Object> modifyReservation(@PathVariable("id") Integer id, @RequestBody PutDTO dto) {
        Map<String, Object> response = new HashMap<>();

        if(reservationService.put(id, dto) > 0) {
            response.put("result", "SUCCESS");
        } else {
            response.put("result", "FAIL");
            response.put("reason", "수정불가");
        }

        return response;
    }

    @RequestMapping(value = "/reservations/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> removeReservation(@PathVariable("id") Integer id) {
        Map<String, Object> response = new HashMap<>();

        if(reservationService.delete(id) > 0) {
            response.put("result", "SUCCESS");
        } else {
            response.put("result", "FAIL");
            response.put("reason", "id를 확인해주세요.");
        }

        return response;
    }

    }












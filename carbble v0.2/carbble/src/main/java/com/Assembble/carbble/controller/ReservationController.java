package com.Assembble.carbble.controller;

import com.Assembble.carbble.dto.PutDTO;
import com.Assembble.carbble.dto.ReservationDTO;
import com.Assembble.carbble.service.ReservationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import javax.validation.Valid;
import java.util.*;

@RestController
@EnableSwagger2
@Validated
@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
public class ReservationController {

    @Autowired
    ReservationServiceImpl service;


    //조회
//    @RequestMapping(value = "/reservations", method = RequestMethod.GET)
//    public List<ReservationDTO> getReservation(){
//
//        return service.getReservation();
//
//        }

    //날짜 조회
  //값이 없는데 PUT요청이 왔을 때 예외처리????하고 싶었으나...
    @RequestMapping(value = "/reservations", method = RequestMethod.PUT)
    public List<ReservationDTO> putReservation(@RequestBody @NotNull PutDTO dto){

            log.info("select reservation:[{}]", service.putReservation(dto.getStartdate(), dto.getEnddate()));
             return service.putReservation(dto.getStartdate(), dto.getEnddate());}


    //저장
    @RequestMapping(value = "/reservations", method = RequestMethod.POST)
    public Map<String, Object> addReservation(@Valid @RequestBody ReservationDTO dto) {
        Map<String, Object> response = new HashMap<>();

        //1은성공 0는 실패

        if (service.addReservation(dto) > 0) {
            response.put("reservation", dto);
            log.info("post reservation:[{}]", "SUCCESS"); //log 뒤에  반환값(0,1)을 받아오니까 저장이 두 번 된다 왜지?

        } else {
            response.put("result", "FAIL");
            response.put("reason", "예약불가능");
            log.info("post reservation:[{}]", "FAIL");
        }

        return response;
    }



    //삭제
    @RequestMapping(value = "/reservations/{id}", method = RequestMethod.DELETE)
    public Map<String, Object> removeReservation(@PathVariable("id") Integer id) {
        Map<String, Object> response = new HashMap<>();


            if (service.removeReservation(id) > 0) {
                response.put("result", "SUCCESS");
                log.info("delete:[{}]", service.removeReservation(id)); //나는 DELETE된 id를 로그찍고 싶었으나 반환이 INT(0,1)이라 성공여부만 알 수 있다. 어떤 게 좋은 건가.
            } else {
                response.put("result", "FAIL");
                response.put("reason", "id를 확인해주세요.");
            }

        return response;
    }





}


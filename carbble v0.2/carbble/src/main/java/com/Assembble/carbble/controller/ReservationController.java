package com.Assembble.carbble.controller;

import com.Assembble.carbble.dto.ReservationPutDTO;
import com.Assembble.carbble.dto.ReservationDTO;
import com.Assembble.carbble.dto.ReservationPutDateDTO;
import com.Assembble.carbble.service.ReservationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import javax.validation.Valid;
import java.util.*;

@RestController
@EnableSwagger2
@Validated
@Slf4j
public class ReservationController {

    @Autowired
    ReservationServiceImpl service;



    //날짜 조회
  //값이 없는데 PUT요청이 왔을 때 없다고 콘솔에 알려주려면?
    // 200 응답답  404에러

    @RequestMapping(value = "/reservations", method = RequestMethod.PUT)
    public ResponseEntity<List<ReservationPutDTO>> putReservation(@RequestBody @NotNull ReservationPutDateDTO dto ){

        List <ReservationPutDTO> reservationDTO = service.putReservation(dto.getStartdate(), dto.getEnddate());

            if(reservationDTO.isEmpty()) {
                log.info("select reservation 404 :[{}]",service.putReservation(dto.getStartdate(), dto.getEnddate()));
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);

            }
            else {
                log.info("select reservation 200:[{}]",service.putReservation(dto.getStartdate(), dto.getEnddate()));
                return new ResponseEntity<>(reservationDTO,HttpStatus.OK); //List를 함께 리턴해야 한다.


            }



    }



    //저장
    //201 생성 응답 400 에러
    //문제)실패해도 성공해도 200 요청을 보내준다 요청이 성공적으로 들어오긴 했으니까.....
    @RequestMapping(value = "/reservations", method = RequestMethod.POST)
    public ResponseEntity<String> addReservation(@Valid @RequestBody ReservationDTO dto){

        if(service.addReservation(dto)>0){
            log.info("post reservation 201:[{}]", "SUCCESS");
            return new ResponseEntity<>(HttpStatus.CREATED);

        }else{
            log.error("post reservation 400:[{}]","FAIL");
            String strErrorBody = "{\"reason\":\"잘못된 요청입니다.\"}";
            return new ResponseEntity<>(strErrorBody,HttpStatus.BAD_REQUEST);
        }

    }



    //삭제
    //204 = HttpStatus.NO_CONTENT 응답 404에러
    @RequestMapping(value = "/reservations/{reservationId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeReservation(@PathVariable ("reservationId")  int reservation_id, int user_id){

        if(service.removeReservation(reservation_id, user_id) > 0){
            log.info("delete reservation 204:[{}]", "SUCCESS");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        else{
            log.error("delete reservation 404:[{}]","FAIL");
            String strErrorBody = "{\"reason\":\"id를 찾을 수 없습니다.\"}";
            return new ResponseEntity<>(strErrorBody,HttpStatus.NOT_FOUND);
        }

    }





}


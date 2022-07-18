package com.Assembble.carbble.controller;


import com.Assembble.carbble.dto.CarPutDAO;
import com.Assembble.carbble.dto.*;
import com.Assembble.carbble.service.DatabaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

@EnableSwagger2
@Validated
@Slf4j
@RestController
public class ReturnController {


    @Autowired
    DatabaseServiceImpl service;


    @RequestMapping(value = "/returns", method = RequestMethod.GET)
    public ResponseEntity<List<ReturnDTO>> getReturnList() {

        List<ReturnDTO> dto = service.getReturn();

        if (dto.isEmpty()) {
            log.info("select return 400:[{}]", dto);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {
            log.info("select return 200:[{}]", dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/returns/{reservationId}", method = RequestMethod.POST)
    public ResponseEntity<String> postReturn(@PathVariable Integer reservationId, @Valid @RequestBody IReturnDTO dto) {

        int car_id = dto.getCar_id();
        CarDTO carDto = service.getCar(car_id);
        ReservationDTO reservationDto = service.selectReservationId(reservationId);
        ReturnDTO returnDto = new ReturnDTO();
        CarPutDAO carPutDTO = new CarPutDAO();

        //reservation_id가 있는지 조회하고 있어야 insert
        if (reservationDto != null) {
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
        } else {
            String strErrorBody = "{\"reason\":\"reservation_id가 없습니다.\"}";
            log.error("return select reservation_id :[{}]", strErrorBody);
            return new ResponseEntity<>(strErrorBody, HttpStatus.NOT_FOUND);
        }

        //주행거리가 누적주행거리보다 작으면 거리는 0, 누적주행거리 업데이트 x.
        if (carDto.getCarMileage() > dto.getMileage()) {
            String strErrorBody = "{\"reason\":\"입력받은 누적거리가 현재 차량 누적거리보다 작습니다.\"}";
            log.error("return distance FAIL:[{}], reservation_id:[{}]",  strErrorBody,reservationId);
            returnDto.setDistance(0);
        } else {
            int distance = dto.getMileage() - carDto.getCarMileage();
            returnDto.setDistance(distance);

            //car_mileage = return.mileage

        }


        if (carDto.getCarMileage() < dto.getMileage()){

            carPutDTO.setCarMileage(dto.getMileage());

            if (service.putCarmileage(car_id, carPutDTO) > 0) {
                log.info("car carmileage insert:[SUCCESS]");


            } else {
                log.error("car carmileage insert :[누적마일리지 수정 실패]");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
    }

        if (service.postReturn(returnDto) > 0) {
            log.info("post return 201:[SUCCESS]");
        } else {
            String strErrorBody = "{\"reason\":\"반납할 수 없습니다.\"}";
            log.error("post return 400:[{}]", strErrorBody);
            return new ResponseEntity<>(strErrorBody, HttpStatus.BAD_REQUEST);
        }


        //reservationId에 해당하는 returnDTO를 가져옴
        ReturnDTO returnDTO = service.selectReturn(returnDto.getReservationId());

        //새로운 객체 reservation_id와 return_id를 가져옴
        ReservationPutReturnIdDTO rdto = new ReservationPutReturnIdDTO();

        //returnDTO를 통해 가져온 resturn_id를 새로운 객체에 넣어주기
        rdto.setReturnId(returnDTO.getReturn_id());

        //reservationId를 넣었을 때 난 오류 - Cannot add or update a child row: a foreign key constraint fails (`carbble`.`reservation_tbl`, CONSTRAINT `reservation_tbl_ibfk_3` FOREIGN KEY (`return_id`) REFERENCES `return_tbl` (`return_id`))
        //returnDto.getReservationId()를 넣어서 해결. 컬럼값이 일관되어야 한다.
        rdto.setReservationId(returnDto.getReservationId());
        log.info("put reservation.return_id, reservation_id:[{}]", rdto);


        if (service.putReservationReturnId(rdto) > 0) {
            log.info("put return_id 201:[SUCCESS]");
            log.info("put reservation.return_id, reservation_id:[{}]", rdto);
            return new ResponseEntity<>(HttpStatus.CREATED);

        } else {
            String strErrorBody = "{\"reason\":\"return_id, reservation_id를 확인해주세요.\"}";
            log.error("put reservation.return_id, reservation_id:[{}]", strErrorBody);
            return new ResponseEntity<>(strErrorBody, HttpStatus.BAD_REQUEST);
        }
    }

}

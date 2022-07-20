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
    public ResponseEntity<List<ReturnDTO>> getReturnList()
    {
        List<ReturnDTO> dto = service.getReturn();

        if (dto.isEmpty()) {
            log.info("select return IS EMPTY :[{}]", dto);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            log.info("select return SUCCESS:[{}]", dto);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
    }


    @RequestMapping(value = "/returns/{reservationId}", method = RequestMethod.POST)
    public ResponseEntity<String> postReturn(@PathVariable Integer reservationId, @Valid @RequestBody BReturnDTO dto)
    {
        int car_id = dto.getCar_id();
        CarDTO carDto = service.getCar(car_id);
        ReservationDTO reservationDto = service.selectReservationId(reservationId);
        ReturnDTO returnDto = new ReturnDTO();
        CarPutDAO carPutDTO = new CarPutDAO();

        //reservation_id가 있는지 조회하고 있어야 insert
        if (reservationDto != null)
        {
            returnDto.setReservationId(reservationId);
            returnDto.setDestination(dto.getDestination());
            returnDto.setParking(dto.getParking());
            returnDto.setRefuelingAmount(dto.getRefueling_amount());
            returnDto.setRefuelingCheck(dto.getRefuelingCheck());
            returnDto.setDamage(dto.getDamage());
            returnDto.setLighting(dto.getLighting());
            returnDto.setDashboard(dto.getDashboard());
            returnDto.setBesides(dto.getBesides());

            //예약 시 차량과 반납 시 차량이 동일한지 확인
            if(reservationDto.getCarId() == dto.getCar_id())
            {
            returnDto.setCar_id(dto.getCar_id());
            }
            else
            {
                String strErrorBody = "{\"reason\":\"차량이 일치하지 않습니다.\"}";
                log.error("return select NOT MATCH car_id :[{}]", strErrorBody);
                return new ResponseEntity<>(strErrorBody, HttpStatus.BAD_REQUEST);
            }
            //마일리지 저장
            returnDto.setMileage(dto.getMileage());
        }
        else
        {
            String strErrorBody = "{\"reason\":\"reservation_id가 없습니다.\"}";
            log.error("return select CAN NOT FIND reservation_id :[{}]", strErrorBody);
            return new ResponseEntity<>(strErrorBody, HttpStatus.NOT_FOUND);
        }

        //주행거리가 누적주행거리보다 작으면 거리 0, 누적주행거리 업데이트 x, 반납 가능
        //어떤 예약이 반납이 늦었는지 확인가능하도록 id log
        if (carDto.getCarMileage() > dto.getMileage())
        {
            String strErrorBody = "{\"reason\":\"입력받은 누적거리가 현재 차량 누적거리보다 작습니다.\"}";
            log.error("return distance FAIL:[{}], reservation_id:[{}]",  strErrorBody,reservationId);
            returnDto.setDistance(0);
        }
        else
        {
            int distance = dto.getMileage() - carDto.getCarMileage();
            returnDto.setDistance(distance);
        }

        //입력받은 누적주행거리가 현재차량의 누적주행거리보다 크다
        if (carDto.getCarMileage() < dto.getMileage())
        {
            //차량의 누적주행거리 변경
            carPutDTO.setCarMileage(dto.getMileage());

            if (service.putCarmileage(car_id, carPutDTO) > 0)
            {
                log.info("car carmileage insert:[SUCCESS]");
            }
            else
            {
                log.error("car carmileage FAIL insert :[{}], car_id:[{}]", carPutDTO.getCarMileage(), car_id);
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }

        if (service.postReturn(returnDto) > 0)
        {
            log.info("post return 201:[SUCCESS]");
        }
        else
        {
            String strErrorBody = "{\"reason\":\"반납할 수 없습니다.\"}";
            log.error("post return FAIL:[{}]", strErrorBody);
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

        if (service.putReservationReturnId(rdto) > 0)
        {
            log.info("put return_id 201:[SUCCESS]");
            log.info("put SUCCESS reservation.return_id, reservation_id:[{}]", rdto);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else
        {
            String strErrorBody = "{\"reason\":\"return_id, reservation_id를 확인해주세요.\"}";
            log.error("put FAIL reservation.return_id, reservation_id:[{}]", strErrorBody);
            return new ResponseEntity<>(strErrorBody, HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/graph/SumRefuelingAmount", method = RequestMethod.PUT)
    public ResponseEntity<List<SumRefuelingDTO>> putGraphOfSumRefuelingAmount(@RequestBody PutDateDTO dto)
    {
        log.info(dto.toString());
        List<SumRefuelingDTO> sumRefuelingDTOList = service.sumRefueling(dto);

        if(sumRefuelingDTOList != null)
        {
            return new ResponseEntity<>(sumRefuelingDTOList, HttpStatus.OK);
        }
        else
        {
            log.error("put NOT EXIST graph of sumRefueling:[기간 내 주유금액의 합이 존재하지 않음]");
            return new ResponseEntity<>( HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/graph/CountRefuelingCheck", method = RequestMethod.PUT)
    public ResponseEntity<List<CountRefuelingDTO>> putGraphOfCountRefuelingCheck(@RequestBody PutDateDTO dto)
    {
        log.info(dto.toString());
        List<CountRefuelingDTO> countRefuelingDTOList = service.countRefueling(dto);

        if(countRefuelingDTOList != null)
        {
            return new ResponseEntity<>(countRefuelingDTOList, HttpStatus.OK);
        }
        else
        {
            log.error("put NOT EXIST graph of sumRefueling:[기간 내 주유기록이 존재하지 않음]");
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }
    }


}

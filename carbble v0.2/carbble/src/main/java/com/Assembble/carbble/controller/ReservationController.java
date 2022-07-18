package com.Assembble.carbble.controller;

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
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@RestController
@EnableSwagger2
@Validated
@Slf4j
public class ReservationController {

    @Autowired
    DatabaseServiceImpl service;


    //날짜 조회
    // 200 응답  404에러
    //startdate, enddate log
    @RequestMapping(value = "/reservations", method = RequestMethod.PUT)
    public ResponseEntity<List<ReservationPutDTO>> putReservation(@RequestBody ReservationPutDateDTO dto ) {

        List<ReservationPutDTO> returnList = new ArrayList<>();

        List<ReservationDTO> reservationDTOList = service.putReservation(dto.getStartdate(), dto.getEnddate());

        //ReservationDTO에 있는 returnId를 뽑아서 리스트로 만든다
        List<Integer> returnIdList = reservationDTOList.stream().map(ReservationDTO::getReturnId).collect(Collectors.toList());

        //null을 제거한다
        while (returnIdList.remove(null)) {
        }

        log.info("reservation-return_id list:[{}]",returnIdList);

        //null을 제거한 returnIdList로 returnDTO를 조회한다.
        List<ReturnDTO> returnDTOList = service.selectReturnByReturnIdList(returnIdList);

        //조회한 returnDTO의 returnId와 ReservationDTO의 returnId가 같은지 조회한다음
        for (ReservationDTO reservationDTO : reservationDTOList) {
            ReservationPutDTO reservationPutDTO = new ReservationPutDTO();
            for (ReturnDTO returnDTO : returnDTOList) {
                if (reservationDTO.getReservationId() == returnDTO.getReservationId()
                        && reservationDTO.getReturnId() != null) {

                    reservationPutDTO.setReservationId(reservationDTO.getReservationId());
                    reservationPutDTO.setCarId(reservationDTO.getCarId());
                    reservationPutDTO.setStartdate(reservationDTO.getStartdate());
                    reservationPutDTO.setEnddate(reservationDTO.getEnddate());
                    reservationPutDTO.setUserId(reservationDTO.getUserId());
                    reservationPutDTO.setPurpose(reservationDTO.getPurpose());
                    reservationPutDTO.setPurposeDetail(reservationDTO.getPurposeDetail());
                    reservationPutDTO.setReservationDate(reservationDTO.getReservationDate());
                    reservationPutDTO.setRentalState(reservationDTO.getRentalState());

                    reservationPutDTO.setReturnDate(returnDTO.getReturnDate());
                    reservationPutDTO.setRefueling_amount(returnDTO.getRefuelingAmount());
                    reservationPutDTO.setDestination(returnDTO.getDestination());
                    reservationPutDTO.setMileage(returnDTO.getMileage());
                    reservationPutDTO.setDistance(returnDTO.getDistance());
                    reservationPutDTO.setParking(returnDTO.getParking());
                    reservationPutDTO.setDamage(returnDTO.getDamage());
                    reservationPutDTO.setLighting(returnDTO.getLighting());
                    reservationPutDTO.setDashboard(returnDTO.getDashboard());
                    reservationPutDTO.setBesides(returnDTO.getBesides());

                    returnList.add(reservationPutDTO);

                }

            }

            if (reservationDTO.getReturnId() == null) {
                reservationPutDTO.setReservationId(reservationDTO.getReservationId());
                reservationPutDTO.setCarId(reservationDTO.getCarId());
                reservationPutDTO.setStartdate(reservationDTO.getStartdate());
                reservationPutDTO.setEnddate(reservationDTO.getEnddate());
                reservationPutDTO.setUserId(reservationDTO.getUserId());
                reservationPutDTO.setPurpose(reservationDTO.getPurpose());
                reservationPutDTO.setRentalState(reservationDTO.getRentalState());
                reservationPutDTO.setPurposeDetail(reservationDTO.getPurposeDetail());
                reservationPutDTO.setReservationDate(reservationDTO.getReservationDate());

                returnList.add(reservationPutDTO);


            }

        }

        if (returnList != null)
        {
            log.info("reservation select:[예약 조회 성공]");
            log.info("reservationPutDTO---ALL--- :[{}]", returnList);

            return new ResponseEntity<>(returnList, HttpStatus.OK);
        }
        else
        {
            log.info("reservation select FAIL:[예약이 존재하지 않음]");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }


    }



//생각1. 리스트끼리 이어 붙인다.
//생각2. 리스트말고 한개씩 받아서 index를 reservation_id로 주고 for문을 돌려서 리스트를 만든다?





    //저장
    //201 생성 응답 400 에러
    //새로운 DTO 정의해서 사용
    @RequestMapping(value = "/reservations", method = RequestMethod.POST)
    public ResponseEntity<String> addReservation(@Valid @RequestBody IReservationDTO dto){

        ReservationDTO reservationDTO = new ReservationDTO();

        reservationDTO.setStartdate(dto.getStartdate());
        reservationDTO.setEnddate(dto.getEnddate());
        reservationDTO.setPurpose(dto.getPurpose());
        reservationDTO.setPurposeDetail(dto.getPurposeDetail());
        reservationDTO.setRentalState(0);
        reservationDTO.setReservationDate(new Date());
        reservationDTO.setUserId(dto.getUserId());
        reservationDTO.setCarId(dto.getCarId());
        reservationDTO.setReturnId(null);


        log.info(reservationDTO.toString());
        log.info(dto.toString());
        log.info(dto.getStartdate().toString());


        //조건 1. 예약일시가 현재보다 클 것
        //해본 것
        // 1. rental_state와 reseravation_date 둘 다 프론트에서 받아서 저장 (가능)
        // 2. rental_state만 받고 reservation_date는 안받음 (안됨) -> 테이블에 디폴트값을 넣어서 다시 만들어 봄 (안됨)
        // 3. reservation_date만 받아서 저장 (가능)
        // 4. 혹시 아래 한줄 때문인가 싶어서 빼봄 (상관없었음)
        // 5. 왜 자동생성 되는 줄 알았던 reservation_date를 설정해주게 되었는가.

        //1.예약일시는 현재시간보다 빠를 수 없음
        if (new Date().before(dto.getStartdate()))
        {
            // 2.중복된 예약은 예약할 수 없음
            if(service.selectCount(reservationDTO) > 0 )
            {
                String strErrorBody = "{\"reason\":\"중복된 예약입니다.\"}";
                log.error("중복 reservation :[{}]", strErrorBody);
                return new ResponseEntity<>(strErrorBody,HttpStatus.BAD_REQUEST);
            }
            else
            {
                log.info("post reservation SUCCESS :[{}]" , dto);
            }
        }
       else
       {
            String strErrorBody = "{\"reason\":\"예약일시가 현재보다 빠릅니다.\"}";
            log.error("fast reservation :[{}]", strErrorBody);
            return new ResponseEntity<>(strErrorBody,HttpStatus.BAD_REQUEST);
       }



       log.info("post reservation content:[{}]", service.addReservation(dto));


        if(service.addReservation(dto)>0){
            log.info("post reservation :[SUCCESS]");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            String strErrorBody = "{\"reason\":\"예약을 할 수 없습니다.\"}";
            log.error("post reservation :[{}]", strErrorBody);
            return new ResponseEntity<>(strErrorBody,HttpStatus.BAD_REQUEST);
        }

    }



    //삭제
    //204 = HttpStatus.NO_CONTENT 응답 404에러
    //user_id 추후에
    //reservation_id log
    @RequestMapping(value = "/reservations/{reservationId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeReservation(@PathVariable int reservationId){
        log.info("delete reservation_id:[{}]", reservationId);

        if(service.removeReservation(reservationId) > 0){
            log.info("delete reservation success:[{}]", reservationId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        }
        else{
            String strErrorBody = "{\"reason\":\"id를 찾을 수 없습니다.\"}";
            log.error("delete reservation 404:[{}]", strErrorBody);
            return new ResponseEntity<>(strErrorBody,HttpStatus.NOT_FOUND);
        }

    }





}


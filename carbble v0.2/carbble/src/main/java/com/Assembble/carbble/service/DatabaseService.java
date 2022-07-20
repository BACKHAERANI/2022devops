package com.Assembble.carbble.service;

import com.Assembble.carbble.dto.CarPutDAO;
import com.Assembble.carbble.dto.*;

import java.util.Date;
import java.util.List;

public interface DatabaseService {

    ///////Member///////////////////////////////////////////////////////
    UserDTO loginUser(MemberDTO dto);


    ///////reservation///////////////////////////////////////////////////////

    List<ReservationDTO> putReservation(Date startdate, Date enddate);


    int addReservation(BReservationDTO dto);

    Integer selectCount(ReservationDTO dto);

    int removeReservation(int reservation_id);


    int putReservationReturnId(ReservationPutReturnIdDTO rdto);

    ReservationDTO selectReservationId(int reservation_id);

    ///////car///////////////////////////////////////////////////////
    List<CarDTO> getCar();

    CarDTO getCar(int car_id);

    int putUseStatus(int car_id, int use_status);

    int putCarmileage(int car_id, CarPutDAO cdto);


    ///////return///////////////////////////////////////////////////////

    List<ReturnDTO> getReturn();

    ReturnDTO selectReturn(int reservation_id);

    int postReturn(ReturnDTO dto);

    List<ReturnDTO> selectReturnByReturnIdList(List<Integer> list);


    List<SumRefuelingDTO> sumRefueling(PutDateDTO dto);

    List<CountRefuelingDTO> countRefueling(PutDateDTO dto);


    ///////user///////////////////////////////////////////////////////


    List<UserDTO> getUser();

    int addUser(UserDTO dto);

    int removeUser(int user_id);

    int modifyPwUser(int user_id, String password);

    int modifyUser(int user_id, UserPutDTO dto);


    int selectCountUser(UserDTO dto);

    ///////check///////////////////////////////////////////////////////
    List<CheckDTO> selectCheck(java.sql.Date startdate, java.sql.Date enddate);

    int insertCheck(CheckDTO dto);


    int updateCheck(int check_id, CheckPutDTO dto);


    int deleteCheck(int check_id);

    ///////emergency///////////////////////////////////////////////////////

    int insertEmergency(EmergencyDTO dto);

    List<EmergencyDTO> selectEmergency(java.sql.Date startdate, java.sql.Date endate);

    int updateEmergency(int emergency_id, EmergencyPutDTO dto);

}




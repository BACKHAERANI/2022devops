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


    int addReservation(IReservationDTO dto);

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

    ///////user///////////////////////////////////////////////////////




    List<UserDTO> getUser();

    int addUser(UserDTO dto);

    int removeUser(int user_id);

    int modifyPwUser(int user_id, String password);

    int modifyUser(int user_id, UserPutDTO dto);

}




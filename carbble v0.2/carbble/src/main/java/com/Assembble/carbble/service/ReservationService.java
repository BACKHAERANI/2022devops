package com.Assembble.carbble.service;

import com.Assembble.carbble.dto.ReservationDTO;
import com.Assembble.carbble.dto.ReservationPutReturnIdDTO;
import com.Assembble.carbble.dto.ReservationPutDTO;

import java.util.Date;
import java.util.List;

public interface ReservationService {



    List<ReservationPutDTO> putReservation(Date startdate, Date enddate);


    int addReservation(ReservationDTO dto);

    int removeReservation(int reservation_id, int user_id);

    int putReservationReturnId(int reservation_id, ReservationPutReturnIdDTO rdto);
}

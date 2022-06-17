package com.Assembble.carbble.service;

import com.Assembble.carbble.dto.ReservationDTO;

import java.util.Date;
import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getReservation();

    List<ReservationDTO> putReservation(Date startdate, Date enddate);


    int addReservation(ReservationDTO dto);

    int removeReservation(int id);

}

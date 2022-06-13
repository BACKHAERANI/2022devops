package com.Assembble.carbble.service;

import com.Assembble.carbble.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {

    List<ReservationDTO> getReservation();

    int addReservation(ReservationDTO dto);

    int removeReservation(int id);

}

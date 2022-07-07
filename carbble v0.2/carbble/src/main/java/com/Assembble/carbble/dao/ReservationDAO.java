package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.ReservationDTO;
import com.Assembble.carbble.dto.ReservationPutReturnIdDTO;
import com.Assembble.carbble.dto.ReservationPutDTO;

import java.util.Date;
import java.util.List;

public interface ReservationDAO {



    List<ReservationPutDTO> select(Date startdate, Date enddate);

    int insert(ReservationDTO dto);


    int delete(int reservation_id, int user_id);


    int update(int reservationId, ReservationPutReturnIdDTO rdto);
}

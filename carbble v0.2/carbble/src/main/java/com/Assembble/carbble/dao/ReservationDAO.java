package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.*;

import java.util.Date;
import java.util.List;

public interface ReservationDAO {



    List<ReservationDTO> select(Date startdate, Date enddate);


    ReservationDTO selectReservationId(int reservation_id);

    Integer selectCount(ReservationDTO dto);

    int insert(BReservationDTO dto);


    int delete(int reservation_id);


    int update(ReservationPutReturnIdDTO rdto);



}

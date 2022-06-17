package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.ReservationDTO;

import java.util.Date;
import java.util.List;

public interface ReservationDAO {

    List<ReservationDTO> select();

    List<ReservationDTO> select2(Date startdate, Date enddate);

    int insert(ReservationDTO dto);


    int delete(int id);
}

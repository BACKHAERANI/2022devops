package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.ReservationDTO;

import java.util.List;

public interface ReservationDAO {

    List<ReservationDTO> select();

    int insert(ReservationDTO dto);

    int delete(int id);
}

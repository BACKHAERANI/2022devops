package com.Assembble.carbble.service;

import com.Assembble.carbble.dao.ReservationDAOImpl;
import com.Assembble.carbble.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationDAOImpl reservation;

    @Override
    public List<ReservationDTO> getReservation()
    {
        return reservation.select();
    }

    @Override
    public int addReservation(ReservationDTO dto)
    {
        return reservation.insert(dto);
    }

    @Override
    public  int removeReservation(int id){return reservation.delete(id);}


}

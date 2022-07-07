package com.Assembble.carbble.service;

import com.Assembble.carbble.dao.ReservationDAOImpl;
import com.Assembble.carbble.dto.ReservationDTO;
import com.Assembble.carbble.dto.ReservationPutReturnIdDTO;
import com.Assembble.carbble.dto.ReservationPutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    ReservationDAOImpl reservation;


    @Override
    public List<ReservationPutDTO> putReservation(Date startdate, Date enddate)
    {
        return reservation.select(startdate, enddate);
    }

    @Override
    public int addReservation(ReservationDTO dto)
    {
        return reservation.insert(dto);
    }


    @Override
    public int removeReservation(int reservation_id, int user_id){return reservation.delete(reservation_id, user_id);}


    @Override
    public int putReservationReturnId(int reservation_id, ReservationPutReturnIdDTO rdto){
        return reservation.update(reservation_id, rdto);
    }
}

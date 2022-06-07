package com.Assambble.carbble.service;


import com.Assambble.carbble.dto.PutDTO;
import com.Assambble.carbble.dto.ReservationDTO;
import com.Assambble.carbble.model.entity.Reservation;
import com.Assambble.carbble.repository.ReservationRepository;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService (ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Transactional(readOnly = true)
    public Optional<Reservation> findById(Integer id) {
        return reservationRepository.findById(id);
    }

    @Transactional
    public List<Reservation> findAll(){
        return reservationRepository.findAll();
    }

    @Transactional
    public Reservation save(ReservationDTO dto) {
        Reservation reservation = new Reservation(dto.getId(), dto.getUsername(), dto.getCar(), dto.getStartdate(),
                dto.getEnddate(),dto.getTimerange(), dto.getPurpose(),  dto.getPurposeDetail());
        return reservationRepository.save(reservation);
    }

    @Transactional
    public int put(Integer id, PutDTO dto) {
        Optional<Reservation> reserv = reservationRepository.findById(id);
        if (reserv.isPresent()) {
            Reservation reservation = reserv.get();
            reservation.setStartdate(dto.getStartdate());
            reservation.setEnddate(dto.getEnddate());
            reservationRepository.save(reservation);
            return 1;
        }
        return 0;
    }

    @Transactional
    public int delete(Integer id) {
        Optional<Reservation> reserv = reservationRepository.findById(id);
        if(reserv.isPresent()) {
            reservationRepository.delete(reserv.get());
            return 1;
        }
        return 0;
    }





}


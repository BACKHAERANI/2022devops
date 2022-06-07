package com.Assambble.carbble.repository;

import com.Assambble.carbble.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findAll();
    Optional<Reservation> findById(Integer id);

}

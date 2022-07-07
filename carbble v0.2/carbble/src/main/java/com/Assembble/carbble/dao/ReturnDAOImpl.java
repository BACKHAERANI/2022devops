package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.CarDTO;
import com.Assembble.carbble.dto.ReturnDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ReturnDAOImpl implements ReturnDAO {


    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<ReturnDTO> select() {
        String strQuery = "SELECT * FROM return_tbl; ";
        return jdbcTemplate.query(
                strQuery,
                new BeanPropertyRowMapper<>(ReturnDTO.class)
        );
    }


    @Override
    public int insertReturn(ReturnDTO dto) {
        String strQuery = "INSERT INTO return_tbl(distance, destination, mileage, parking, refueling_amount,damage,lighting,dashboard,besides, reservation_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        return jdbcTemplate.update(strQuery, dto.getDistance(), dto.getDestination(), dto.getMileage(), dto.getParking(), dto.getRefuelingAmount(), dto.getDamage(), dto.getLighting(), dto.getDashboard(), dto.getBesides(), dto.getReservationId()
                );
    }
}






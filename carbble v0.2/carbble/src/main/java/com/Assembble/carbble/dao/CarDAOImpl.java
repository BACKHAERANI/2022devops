package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.CarDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDAOImpl implements CarDAO{


    @Autowired
    JdbcTemplate jdbcTemplate;

//차에 대한 정보를 보여주어야 한다.
    @Override
    public List<CarDTO> select()

    {
        String strQuery = "SELECT * FROM car_tbl";
        return jdbcTemplate.query(strQuery, new BeanPropertyRowMapper<>(CarDTO.class));

    }

    //미사용시, 관리자가 사용상태를 변경할 수 있어야 한다.
    @Override
    public int update(int car_id)

    {
        String strQuery = "UPDATE car_tbl SET use_status = 1 WHERE car_id=?";

        return jdbcTemplate.update(strQuery, car_id );

    }

    @Override
    public CarDTO select(int car_id) {
        String strQuery = "SELECT * FROM car_tbl where car_id=?";
        return jdbcTemplate.queryForObject(strQuery, new BeanPropertyRowMapper<>(CarDTO.class), new Object[]{car_id});
    }

    @Override
    public int updatemileage(int car_id, CarPutDTO cdto) {
        String strQuery = "UPDATE car_tbl SET car_mileage =? where car_id=?";
        return jdbcTemplate.update(strQuery, cdto.getCarMileage() ,car_id);
    }
}

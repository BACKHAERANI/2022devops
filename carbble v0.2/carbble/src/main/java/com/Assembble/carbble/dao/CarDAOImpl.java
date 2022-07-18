package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.CarDTO;
import com.Assembble.carbble.dto.CarPutDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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
    //프론트에서 사용하지 않음
    //1 사용중 0 미사용
    //제한적
    //함수이름 update use_status로 변경
    @Override
    public int updateUseStatus(int car_id, int use_status)

    {
        String strQuery = "UPDATE car_tbl SET use_status = ? WHERE car_id=?";

        return jdbcTemplate.update(strQuery, use_status, car_id );

    }

    //해당 차량에 대한 정보 - returnController에서 사용 중
    //queryForObject  한줄의 행만 처리
    //EmptyResultDataAccessException - queryForObject일 경우 값이 없어서 발생
    @Override
    public CarDTO select(int car_id) {
        try{
        String strQuery = "SELECT * FROM car_tbl where car_id=?";
        return jdbcTemplate.queryForObject(strQuery,
                new BeanPropertyRowMapper<>(CarDTO.class),
                new Object[]{car_id});

        } catch (EmptyResultDataAccessException e){
            return  null;
        }
    }

    @Override
    public int updatemileage(int car_id, CarPutDAO cdto) {
        String strQuery = "UPDATE car_tbl SET car_mileage =? where car_id=?";
        return jdbcTemplate.update(strQuery, cdto.getCarMileage() ,car_id);
    }
}

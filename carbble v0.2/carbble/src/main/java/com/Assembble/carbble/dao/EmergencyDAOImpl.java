package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.EmergencyDTO;
import com.Assembble.carbble.dto.EmergencyPutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class EmergencyDAOImpl implements EmergencyDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;


    @Override
    public List<EmergencyDTO> selectEmergency(Date startdate, Date enddate)
    {
        String strQuery = "SELECT * FROM emergency_tbl WHERE emergency_date >= ? AND emergency_date <= ?;";
        return jdbcTemplate.query(strQuery, new BeanPropertyRowMapper<>(EmergencyDTO.class), new Object[]{startdate, enddate});
    }

    @Override
    public int insertEmergency(EmergencyDTO dto)
    {
        String strQuery = "INSERT INTO emergency_tbl (emergency_date, emergency_content, car_id) VALUES (?,?,?);";
        return jdbcTemplate.update(strQuery, dto.getEmergencyDate(), dto.getEmergencyContent(), dto.getCarId());
    }

    @Override
    public int updateEmergency(int emergency_id, EmergencyPutDTO dto)
    {
        String condition = "";
        String condition1 = "";
        String condition2 = "";
        String condition3 = "";


        if(dto.getEmergencyContent() != null){
            condition1 = String.format("check_content = '%s'", dto.getEmergencyContent());
            condition = condition1;
        }
        if(dto.getEmergencyDate() != null){
            condition2 = String.format("check_date = '%s'", dto.getEmergencyDate());
            condition = condition2;
        }
        if(dto.getCarId() != 0){
            condition3 = String.format("check_content = '%s'", dto.getCarId());
            condition = condition3;
        }
        if(!condition1.equals("") && !condition2.equals("")){
            condition = condition1 + "," + condition2 + "," + condition3;
        }

        String strQuery = "UPDATE check_tbl SET " + condition + " WHERE check_id=? ";

        return jdbcTemplate.update(strQuery, emergency_id);

    }


}

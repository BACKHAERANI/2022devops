package com.Assembble.carbble.dao;


import com.Assembble.carbble.dto.CheckDTO;
import com.Assembble.carbble.dto.CheckPutDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public class CheckDAOImpl implements CheckDAO{


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<CheckDTO> selectCheck(Date startdate, Date enddate)
    {
        String strQuery = "SELECT * FROM check_tbl WHERE check_date >= ? AND check_date <= ?;";
        return jdbcTemplate.query(
                strQuery,
                new BeanPropertyRowMapper<>(CheckDTO.class),
                new Object[]{startdate, enddate}
        );

    }

    @Override
    public int insertCheck(CheckDTO dto)
    {
        String strQuery = "INSERT INTO check_tbl (check_date, check_content, check_mileage, car_id) VALUES (?,?,?,?);";

        return jdbcTemplate.update(strQuery, dto.getCheckDate(), dto.getCheckContent(), dto.getCheckMileage(),dto.getCarId());
    }



    @Override
    public int updateCheck(int check_id, CheckPutDTO dto)
    {
        String condition = "";
        String condition1 = "";
        String condition2 = "";
        String condition3 = "";


        if(dto.getCheckContent() != null){
            condition1 = String.format("check_content = '%s'", dto.getCheckContent());
            condition = condition1;
        }
        if(dto.getCheckDate() != null){
            condition2 = String.format("check_date = '%s'", dto.getCheckDate());
            condition = condition2;
        }
        if(dto.getCheckMileage() != 0){
            condition3 = String.format("check_content = '%s'", dto.getCheckMileage());
            condition = condition3;
        }
        if(!condition1.equals("") && !condition2.equals("")){
            condition = condition1 + "," + condition2 + "," + condition3;
        }

        String strQuery = "UPDATE check_tbl SET " + condition + " WHERE check_id=? ";

        return jdbcTemplate.update(strQuery, check_id);


    }


    @Override
    public int deleteCheck(int check_id)
    {
        String strQuery = "DELETE FROM check_tbl WHERE check_id = ?;";

        return jdbcTemplate.update(strQuery,check_id);

    }


}

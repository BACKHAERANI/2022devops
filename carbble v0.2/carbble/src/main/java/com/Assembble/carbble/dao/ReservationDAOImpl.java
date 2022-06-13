package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.ReservationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationDAOImpl implements ReservationDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;



    @Override
    public List<ReservationDTO> select()
    {
        String strQuery = "SELECT * FROM reservation_tbl WHERE startdate > DATE_ADD(NOW(), INTERVAL -3 WEEK) AND enddate < DATE_ADD(NOW(), INTERVAL +1 WEEK);";
        return jdbcTemplate.query(strQuery, new BeanPropertyRowMapper<>(ReservationDTO.class));
    }

    @Override
    public int insert(ReservationDTO dto)
    {
        String strQuery = "INSERT INTO reservation_tbl(id, username, car, startdate, enddate, timerange, purpose, purpose_detail) VALUES (?, ?, ?, ?, ?,?,?,?)";
        return jdbcTemplate.update(strQuery, dto.getId(), dto.getUsername(), dto.getCar(), dto.getStartdate(), dto.getEnddate(), dto.getTimerange(), dto.getPurpose(), dto.getPurposeDetail());
    }


    @Override
    public int delete(int id)
    {
        String strQuery = "DELETE FROM reservation_tbl WHERE id=?";
        return jdbcTemplate.update(strQuery, id);
    }

}

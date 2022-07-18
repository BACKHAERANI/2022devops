package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public class ReservationDAOImpl implements ReservationDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;



    //프론트에서 넣어준 날짜로 PUT
    @Override
    public List<ReservationDTO> select(Date startdate, Date enddate)
    {

        String strQuery = "SELECT * FROM reservation_tbl WHERE startdate >= ? AND enddate <= ? ORDER BY startdate DESC, enddate DESC;";

        return jdbcTemplate.query(
                strQuery,
                new BeanPropertyRowMapper<>(ReservationDTO.class), //ResultSet -> Bean
                new Object[]{startdate, enddate});
    }



    @Override
    public ReservationDTO selectReservationId(int reservation_id) {

        try{

            String strQuery = "SELECT * FROM reservation_tbl WHERE reservation_id = ?; ";
            return jdbcTemplate.queryForObject(
                    strQuery,
                    new BeanPropertyRowMapper<>(ReservationDTO.class),
                    new Object[]{reservation_id}
            );}
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }



    @Override
    public Integer selectCount(ReservationDTO dto){

        String strQuery = "SELECT COUNT(*) AS count FROM reservation_tbl WHERE startdate < ? AND enddate > ? AND car_id = ?;";
        try
        {
            ReservationCountDTO count = jdbcTemplate.queryForObject(
                    strQuery,
                    new BeanPropertyRowMapper<>(ReservationCountDTO.class),
                    new Object[]{ dto.getEnddate(), dto.getStartdate(), dto.getCarId()}
            );

            return count.getCount();
        }
        catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }


    @Override
    public int insert(IReservationDTO dto)
    {

        String strQuery = "INSERT INTO reservation_tbl (startdate, enddate, purpose, purpose_detail, car_id, user_id) VALUES ( ?, ?, ?, ?, ?, ?);";
        return jdbcTemplate.update(strQuery, dto.getStartdate(), dto.getEnddate(), dto.getPurpose(), dto.getPurposeDetail(),
                dto.getCarId(), dto.getUserId() );
    }


    @Override
    public int delete(int reservation_id)
    {
        String strQuery = "DELETE FROM reservation_tbl WHERE reservation_id=?";
        return jdbcTemplate.update(strQuery, reservation_id);
    }


    @Override
    public int update(ReservationPutReturnIdDTO rdto){

        String strQuery = "UPDATE reservation_tbl SET return_id=?, rental_State=1 WHERE reservation_id=?";


        return jdbcTemplate.update(strQuery, rdto.getReturnId(),rdto.getReservationId());

    }



}





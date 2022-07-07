package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.ReservationDTO;
import com.Assembble.carbble.dto.ReservationPutReturnIdDTO;
import com.Assembble.carbble.dto.ReservationPutDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ReservationPutDTO> select(Date startdate, Date enddate)
    {

        String strQuery = "SELECT reservation_tbl.reservation_id, car_name,startdate, enddate, purpose, purpose_detail, reservation_date ,return_date, username, distance,destination, refueling_amount, mileage, damage,lighting, dashboard,besides  \n" +
                "FROM reservation_tbl \n" +
                "INNER JOIN user_tbl \n" +
                "ON reservation_tbl.user_id = user_tbl.user_id \n" +
                "INNER JOIN car_tbl \n" +
                "ON reservation_tbl.car_id = car_tbl.car_id \n" +
                "LEFT JOIN return_tbl \n" +
                "ON reservation_tbl.return_id = return_tbl.return_id \n" +
                "WHERE startdate >= ? AND enddate <= ? ORDER BY startdate DESC, enddate DESC ";

        return jdbcTemplate.query(
                strQuery,
                new BeanPropertyRowMapper<>(ReservationPutDTO.class), //ResultSet -> Bean
                new Object[]{startdate, enddate});
    }




    //시간이 겹치면 같은 차량을 예약할 수 없다
   public int insert(ReservationDTO dto)

    {
        Integer rentalstate = Integer.valueOf(0);

        if(dto.getReturnId() == null){
            rentalstate = 0;
        }else{
           rentalstate = 1;
        }


        String strQuery = "INSERT INTO reservation_tbl (reservation_id, startdate, enddate, purpose, purpose_detail, rental_state, reservation_date, car_id, user_id, return_id) SELECT ?, ?, ?, ?, ?, ?, ?,?,?,? FROM DUAL WHERE NOT EXISTS(SELECT  startdate, enddate FROM reservation_tbl WHERE car_id = ? AND((startdate BETWEEN ? AND ?) OR (enddate BETWEEN ? AND ?) OR (startdate < ? AND enddate > ?)))";
        return jdbcTemplate.update(strQuery, dto.getReservationId(), dto.getStartdate(), dto.getEnddate(), dto.getPurpose(), dto.getPurposeDetail(), rentalstate, dto.getReservationDate(), dto.getCarId(), dto.getUserId(), dto.getReturnId(), dto.getCarId(),dto.getStartdate(), dto.getEnddate(),
                                     dto.getStartdate(), dto.getEnddate(), dto.getStartdate(), dto.getEnddate());
    }


    @Override
    public int delete(int reservation_id, int user_id)
    {
        String strQuery = "DELETE FROM reservation_tbl WHERE reservation_id=? AND user_id=?";
        return jdbcTemplate.update(strQuery, reservation_id, user_id);
    }


    @Override
    public int update(int reservationId, ReservationPutReturnIdDTO rdto){

        String strQuery = "UPDATE reservation_tbl SET return_id=?, rental_State=1 WHERE reservation_id=?";
        return jdbcTemplate.update(strQuery, rdto.getReturnId(),reservationId);
    }



}





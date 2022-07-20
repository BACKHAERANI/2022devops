package com.Assembble.carbble.dao;
import com.Assembble.carbble.dto.CountRefuelingDTO;
import com.Assembble.carbble.dto.PutDateDTO;
import com.Assembble.carbble.dto.ReturnDTO;
import com.Assembble.carbble.dto.SumRefuelingDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
public class ReturnDAOImpl implements ReturnDAO {


    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    //프론트에서 사용하지 않음
    @Override
    public List<ReturnDTO> select() {
        String strQuery = "SELECT * FROM return_tbl; ";
        return jdbcTemplate.query(
                strQuery,
                new BeanPropertyRowMapper<>(ReturnDTO.class)
        );
    }


    @Override
    public ReturnDTO selectReturnId(int reservation_id) {

        try{
            String strQuery = "SELECT * FROM return_tbl WHERE reservation_id = ?; ";
            return jdbcTemplate.queryForObject(
                    strQuery,
                    new BeanPropertyRowMapper<>(ReturnDTO.class),
                    new Object[]{reservation_id}
            );
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }



    @Override
    public List<ReturnDTO> selectReturnByReturnIdList(List<Integer> list) {

        try {


            SqlParameterSource params = new MapSqlParameterSource("list", list);

            String strQuery = "SELECT * FROM return_tbl WHERE return_id IN (:list);";


            List<ReturnDTO> returnDTOList = namedParameterJdbcTemplate.query(strQuery,
                    params, BeanPropertyRowMapper.newInstance(ReturnDTO.class));

            System.out.println(returnDTOList);

            return returnDTOList;
        } catch (BadSqlGrammarException e) {
            return null;
        }
    }




    @Override
    public int insertReturn(ReturnDTO dto) {
        String strQuery = "INSERT INTO return_tbl( car_id,  distance, destination, mileage, parking, refueling_check ,refueling_amount, damage,lighting,dashboard,besides, reservation_id) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

        return jdbcTemplate.update(strQuery, dto.getCar_id(), dto.getDistance(), dto.getDestination(), dto.getMileage(), dto.getParking(),dto.getRefuelingCheck(), dto.getRefuelingAmount(), dto.getDamage(), dto.getLighting(), dto.getDashboard(), dto.getBesides(), dto.getReservationId()
                );
    }




    @Override
    public List<SumRefuelingDTO> sumRefueling(PutDateDTO dto){

        String strQuery = "SELECT sum(refueling_amount) AS sum, car_id FROM return_tbl  WHERE ? < return_date AND return_date < ? GROUP BY car_id;";
        return jdbcTemplate.query(
                strQuery,
                new BeanPropertyRowMapper<>(SumRefuelingDTO.class),
                new Object[]{dto.getStartdate(), dto.getEnddate()}
        );
}



@Override
    public List<CountRefuelingDTO> countRefueling(PutDateDTO dto){

        String strQuery = "SELECT count(refueling_check) AS count, car_id FROM return_tbl WHERE refueling_check=TRUE AND ? < return_date AND return_date < ? GROUP BY car_id;";

    return jdbcTemplate.query(
            strQuery,
            new BeanPropertyRowMapper<>(CountRefuelingDTO.class),
            new Object[]{dto.getStartdate(), dto.getEnddate()}
    );
}






}





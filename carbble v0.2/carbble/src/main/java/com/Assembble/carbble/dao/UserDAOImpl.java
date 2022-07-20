package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    JdbcTemplate jdbcTemplate;

//비밀번호 빼기
    @Override
    public List<UserDTO> userSelect()
    {

        String strQuery = "SELECT user_id, username, partname, position, telephone,authority FROM user_tbl";
        return jdbcTemplate.query(strQuery, new BeanPropertyRowMapper<>(UserDTO.class));

    }

    @Override
    public Integer selectCountUser(UserDTO dto){

        String strQuery = "SELECT COUNT(*) AS count FROM user_tbl WHERE user_id = ?;";
        try
        {
            CountDTO count = jdbcTemplate.queryForObject(
                    strQuery,
                    new BeanPropertyRowMapper<>(CountDTO.class),
                    new Object[]{dto.getUserId()}
            );

            return count.getCount();
        }
        catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }


    public int userInsert(UserDTO dto)
    {
        String strQuery = "INSERT INTO user_tbl(user_id, password ,username, partname, position, telephone,authority) VALUES (?, HEX(AES_ENCRYPT(?, SHA2(\"enc_key\", 256))) , ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(strQuery, dto.getUserId(), dto.getPassword(), dto.getUsername(), dto.getPartname(), dto.getPosition(), dto.getTelephone(),dto.getAuthority());

    }




    @Override
    public int userDelete(int user_id)
    {
        String strQuery = "DELETE FROM user_tbl WHERE user_id=?";
        return jdbcTemplate.update(strQuery, user_id);
    }



    @Override
    public int userPwUpdate(int user_id, String password)
    {
//        String str = password;
//        str = str.replaceAll("\"","");
//
//        System.out.println(str);


        String strQuery = "UPDATE user_tbl SET password = HEX(AES_ENCRYPT(?, SHA2(\"enc_key\", 256))) WHERE user_id=?" ;
        return jdbcTemplate.update(strQuery, password, user_id);

    }


    @Override
    public int userUpdate(int user_id, UserPutDTO dto) {

        String condition = "";
        String condition1 = "";
        String condition2 = "";
        String condition3 = "";


        if (dto.getPartname() != null) {
            condition1 = String.format("partname = '%s'", dto.getPartname());
            condition = condition1;
        }
        if (dto.getPosition() != null) {
            condition2 = String.format("position = '%s'", dto.getPosition());
            condition = condition2;
        }
        if (dto.getTelephone() != null) {
            condition3 = String.format("telephone = '%s'", dto.getTelephone());
            condition = condition3;}
        if (!condition1.equals("") && !condition2.equals("")) {
            condition = condition1 + "," + condition2 + "," + condition3;

        }


            String strQuery = "UPDATE user_tbl SET " + condition + " WHERE user_id=? ";



            return jdbcTemplate.update(strQuery, user_id);

        }

    }





package com.Assembble.carbble.dao;


import com.Assembble.carbble.dto.MemberDTO;
import com.Assembble.carbble.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDAOImpl implements MemberDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;


    //하고싶은것: 카운트 1이 있으면 201요청보내기
//주소창에 비번이 뜬다
    @Override
    public UserDTO select(MemberDTO dto) {

        try {
            String strQuery = "SELECT * FROM user_tbl WHERE user_id=? AND password = HEX(AES_ENCRYPT(?, SHA2(\"enc_key\", 256)))";

            return jdbcTemplate.queryForObject(
                    strQuery,
                    new BeanPropertyRowMapper<>(UserDTO.class),
                    new Object[]{dto.getId(), dto.getPassword()});


        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }
}


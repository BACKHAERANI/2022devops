package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.UserDTO;

import java.util.List;

public interface MemberDAO {


    //하고싶은것: 카운트 1이 있으면 201요청보내기
    //주소창에 비번이 뜬다
    List<UserDTO> select(Integer id, String password);


}

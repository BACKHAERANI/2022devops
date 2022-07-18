package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.UserDTO;
import com.Assembble.carbble.dto.UserPutDTO;

import java.util.List;

public interface UserDAO {


    List<UserDTO> userSelect();

    int userInsert(UserDTO dto);

    int userDelete(int user_id);

    int userPwUpdate(int user_id, String password);

    int userUpdate(int user_id, UserPutDTO dto);
}

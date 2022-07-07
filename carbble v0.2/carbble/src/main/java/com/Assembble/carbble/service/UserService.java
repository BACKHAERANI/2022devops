package com.Assembble.carbble.service;

import com.Assembble.carbble.dto.UserDTO;
import com.Assembble.carbble.dto.UserPutDTO;
import com.Assembble.carbble.dto.UserPwDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getUser();


    int addUser(UserDTO dto);

    int removeUser(int user_id);

    int modifyPwUser(int user_id, UserPwDTO dto);

    int modifyUser(int user_id, UserPutDTO dto);
}

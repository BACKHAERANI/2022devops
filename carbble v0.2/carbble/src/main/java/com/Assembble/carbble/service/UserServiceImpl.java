package com.Assembble.carbble.service;

import com.Assembble.carbble.dao.UserDAOImpl;
import com.Assembble.carbble.dto.UserDTO;
import com.Assembble.carbble.dto.UserPutDTO;
import com.Assembble.carbble.dto.UserPwDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAOImpl user;

    @Override
    public List<UserDTO> getUser(){

        return user.userSelect();
    }

    @Override
    public int addUser(UserDTO dto){

        return user.userInsert(dto);
    }

    @Override
    public int removeUser(int user_id){

        return user.userDelete(user_id);
    }

    @Override
    public int modifyPwUser(int user_id, UserPwDTO dto){
        return user.userPwUpdate(user_id, dto);
    }

    @Override
    public int modifyUser(int user_id, UserPutDTO dto){
        return user.userUpdate(user_id, dto);
    }

}

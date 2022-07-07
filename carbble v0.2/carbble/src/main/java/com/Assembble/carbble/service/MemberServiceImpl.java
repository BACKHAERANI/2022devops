package com.Assembble.carbble.service;

import com.Assembble.carbble.dao.MemberDAOImpl;
import com.Assembble.carbble.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberServiceImpl implements MemberService{

    @Autowired
    MemberDAOImpl member;

    @Override
    public List<UserDTO> loginUser(Integer id, String password){
        return member.select(id, password);
    }
}

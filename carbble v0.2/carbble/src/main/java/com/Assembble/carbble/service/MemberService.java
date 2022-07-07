package com.Assembble.carbble.service;

import com.Assembble.carbble.dto.UserDTO;

import java.util.List;

public interface MemberService {


   List<UserDTO> loginUser(Integer id, String password);
}

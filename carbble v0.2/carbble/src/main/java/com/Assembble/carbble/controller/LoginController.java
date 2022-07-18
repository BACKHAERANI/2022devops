package com.Assembble.carbble.controller;

import com.Assembble.carbble.dto.MemberDTO;
import com.Assembble.carbble.dto.UserDTO;
import com.Assembble.carbble.service.DatabaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

@RestController
@EnableSwagger2
@Validated
@Slf4j
public class LoginController {


    @Autowired
    DatabaseServiceImpl service;

//값이 1개 리스트일 필요 x
    //jwt 공부....
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public  ResponseEntity<UserDTO>loginUser(@RequestBody MemberDTO dto){

        UserDTO userDTO = service.loginUser(dto);

        if (userDTO != null ) {
            log.info("login 201:[SUCCESS]");
            return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
        } else {
            String strErrorBody = "{\"reason\":\"유저 정보를 찾을 수 없음\"}";
            log.error("login 404:[{}]", strErrorBody);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}

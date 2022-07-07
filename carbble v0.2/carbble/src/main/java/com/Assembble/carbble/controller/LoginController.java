package com.Assembble.carbble.controller;

import com.Assembble.carbble.dto.UserDTO;
import com.Assembble.carbble.service.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    MemberServiceImpl service;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public  ResponseEntity<List<UserDTO>>loginUser(Integer id, String password){


        System.out.println("login:");
        System.out.println(id);
        System.out.println(password);
        System.out.println(service.loginUser(id, password));

        List <UserDTO>UserDTO = service.loginUser(id, password);
        if (!UserDTO.isEmpty()) {
            log.info("login 201:[{}]", "SUCCESS");
            return new ResponseEntity<>(UserDTO,HttpStatus.CREATED);
        } else {
            log.error("login 404:[{}]", "FAIL");
            String strErrorBody = "{\"reason\":\"로그인불가\"}";
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



}

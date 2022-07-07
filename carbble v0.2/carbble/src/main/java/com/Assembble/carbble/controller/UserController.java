package com.Assembble.carbble.controller;

import com.Assembble.carbble.dto.*;
import com.Assembble.carbble.service.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

@RestController
@EnableSwagger2
@Validated
@Slf4j
public class UserController {


    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> userList() {

        List<UserDTO> userDTO = userService.getUser();

        if (userDTO.isEmpty()) {
            log.info("select User 400:[{}]", userService.getUser());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {
            log.info("select User 200:[{}]", userService.getUser());
            return new ResponseEntity<>(userDTO, HttpStatus.OK);


        }
    }


    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@Valid @RequestBody UserDTO dto) {

        if (userService.addUser(dto) > 0) {
            log.info("post user 201:[{}]", "SUCCESS");
            return new ResponseEntity<>(HttpStatus.CREATED);

        } else {
            log.error("post user 400:[{}]", "FAIL");
            String strErrorBody = "{\"reason\":\"잘못된 요청입니다.\"}";
            return new ResponseEntity<>(strErrorBody, HttpStatus.BAD_REQUEST);
        }

    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeUser(@PathVariable("userId") Integer user_id) {

        if (userService.removeUser(user_id) > 0) {
            log.info("delete user 204:[{}]", "SUCCESS");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } else {
            log.error("delete user 404:[{}]", "FAIL");
            String strErrorBody = "{\"reason\":\"id를 찾을 수 없습니다.\"}";
            return new ResponseEntity<>(strErrorBody, HttpStatus.NOT_FOUND);
        }

    }


    @RequestMapping(value = "/users/pw/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<String>  modifyPwUser(@PathVariable("userId") Integer user_id, UserPwDTO dto) {

        try {
            if (userService.modifyPwUser(user_id, dto) > 0) {
                log.info("put user 200:[{}]", "SUCCESS");
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else{
                log.error("put user 404:[{}]", "FAIL");
                String strErrorBody = "{\"reason\":\"id를 찾을 수 없습니다.\"}";
                return new ResponseEntity<>(strErrorBody, HttpStatus.NOT_FOUND);
            }

        } catch (NoSuchMethodError e) {
            //Error? Exception?
            log.error("put user 400:[{}]", "FAIL");
            String strErrorBody = "{\"reason\":\"잘못된 요청입니다.\"}";
            return new ResponseEntity<>(strErrorBody, HttpStatus.BAD_REQUEST);
        }
    }





    @RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<String> modifyUser(@PathVariable("userId") int user_id, UserPutDTO dto) {
        System.out.println("controll : ");
        System.out.println(user_id);

        if (userService.modifyUser(user_id,dto)>0) {
            log.info("put user 200:[{}]", "SUCCESS");
            return new ResponseEntity<>(HttpStatus.OK);

        } else {
            log.error("put user 404:[{}]", "FAIL");
            String strErrorBody = "{\"reason\":\"id를 찾을 수 없습니다.\"}";
            return new ResponseEntity<>(strErrorBody, HttpStatus.NOT_FOUND);
        }

    }




}

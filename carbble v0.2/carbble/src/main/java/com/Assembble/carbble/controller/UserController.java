package com.Assembble.carbble.controller;

import com.Assembble.carbble.dto.*;
import com.Assembble.carbble.service.DatabaseServiceImpl;
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
    DatabaseServiceImpl userService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ResponseEntity<List<UserDTO>> userList() {

        List<UserDTO> userDTO = userService.getUser();

        if (userDTO.isEmpty()) {
            log.info("select User 400:[{}]", userDTO );
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        } else {
            log.info("select User 200:[{}]", userDTO );
            return new ResponseEntity<>(userDTO, HttpStatus.OK);


        }
    }


    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@Valid @RequestBody UserDTO dto) {

        if (userService.addUser(dto) > 0) {
            log.info("post user 201:[SUCCESS]");
            return new ResponseEntity<>(HttpStatus.CREATED);

        } else {
            //정확한 이유를 표기
            String strErrorBody = "{\"reason\":\"사용자를 등록할 수 없습니다.\"}";
            log.error("post user 400:[{}]", strErrorBody );
            return new ResponseEntity<>(strErrorBody, HttpStatus.BAD_REQUEST);
        }

    }

    //user_id log
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeUser(@PathVariable("userId") Integer user_id) {

        log.info("delete user user_id:[{}]", user_id);

        if (userService.removeUser(user_id) > 0) {
            log.info("delete user 204:[SUCCESS]");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            String strErrorBody = "{\"reason\":\"id를 찾을 수 없습니다.\"}";
            log.error("delete user 404:[{}]", strErrorBody);
            return new ResponseEntity<>(strErrorBody, HttpStatus.NOT_FOUND);
        }

    }

//user_id
    //String password
    @RequestMapping(value = "/users/pw/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<String> modifyPwUser(@PathVariable("userId") Integer user_id, @RequestBody String password) {

        log.info("change pw user_id:[{}]", user_id);

            if (userService.modifyPwUser(user_id, password) > 0) {
                log.info("put user pw modify :[SUCCESS]");
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else
            {
                String strErrorBody = "{\"reason\":\"user_id를 찾을 수 없습니다.\"}";
                log.error("put user pw modify Fail:[{}]", strErrorBody);
                return new ResponseEntity<>(strErrorBody, HttpStatus.NOT_FOUND);
            }

    }





    @RequestMapping(value = "/users/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<String> modifyUser(@PathVariable("userId") int user_id, @RequestBody UserPutDTO dto) {

        log.info("put user tel,position,partname modify user_id:[{}]",user_id);
        log.info("put user partname modify:[{}]", dto.getPartname());
        log.info("put user position modify:[{}]", dto.getPosition());
        log.info("put user tel modify:[{}]", dto.getTelephone());


        if (userService.modifyUser(user_id,dto)>0)
        {
            log.info("put user tel,position,partname:[SUCCESS]");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            String strErrorBody = "{\"reason\":\"user_id를 찾을 수 없습니다.\"}";
            log.error("put user tel,position,partname modify Fail:[{}]", strErrorBody);
            return new ResponseEntity<>(strErrorBody, HttpStatus.NOT_FOUND);
        }

    }




}

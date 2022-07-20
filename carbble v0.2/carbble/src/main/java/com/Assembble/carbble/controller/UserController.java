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
import sun.security.util.Password;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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


        Boolean validPassword = Pattern.matches("^[0-9a-zA-Z#@!$&*^]*$", dto.getPassword());
        log.info("post user password valid : [{}]",validPassword);

        if(userService.selectCountUser(dto) > 0)
        {
            String strErrorBody = "{\"reason\":\"해당 아이디가 존재합니다.\"}";
            log.error("post user EXIST user_id :[{}]", strErrorBody);
            return new ResponseEntity<>(strErrorBody, HttpStatus.BAD_REQUEST);
        }else {

            if (validPassword == true) {
                if (userService.addUser(dto) > 0) {
                    log.info("post user :[SUCCESS]");
                    return new ResponseEntity<>(HttpStatus.CREATED);

                } else {
                    String strErrorBody = "{\"reason\":\"사용자를 등록할 수 없습니다.\"}";
                    log.error("post user NOT CREATE :[{}]", strErrorBody);
                    return new ResponseEntity<>(strErrorBody, HttpStatus.BAD_REQUEST);
                }
            } else {

                String strErrorBody = "{\"reason\":\"비밀번호는 띄어쓰기없이 숫자, 알파벳, 특수기호 #@!$&*^만을 포함합니다.\"}";
                log.error("post user password valid FAIL:[{}]", strErrorBody);
                return new ResponseEntity<>(strErrorBody, HttpStatus.BAD_REQUEST);
            }
        }


    }





    //user_id log
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeUser(@PathVariable("userId") Integer user_id) {

        log.info("delete user user_id:[{}]", user_id);

        if (userService.removeUser(user_id) > 0) {
            log.info("delete user :[SUCCESS]");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            String strErrorBody = "{\"reason\":\"id를 찾을 수 없습니다.\"}";
            log.error("delete user FAIL:[{}]", strErrorBody);
            return new ResponseEntity<>(strErrorBody, HttpStatus.NOT_FOUND);
        }

    }

//user_id
    //String password
    @RequestMapping(value = "/users/pw/{userId}", method = RequestMethod.PUT)
    public ResponseEntity<String> modifyPwUser(@PathVariable("userId") Integer user_id, @RequestBody Map<String, Object> param) {

//        log.info("change pw user_id:[{}]", user_id);
        log.info("change pw user_id:[{}]", param.get("password"));



            if (userService.modifyPwUser(user_id, param.get("password").toString()) > 0) {
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

        log.info("put user ...user_id:[{}]",user_id);
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

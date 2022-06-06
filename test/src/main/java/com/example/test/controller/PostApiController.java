package com.example.test.controller;

import com.example.test.dto.TestDTO;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
public class PostApiController {

//    @PatchMapping("/post")
//    public void post1(@RequestBody TestDTO requestData){
//        // get과 다르게 객체를 사용하여 받아와도 RequestBody를 입력해줘야한다.
//        System.out.println(requestData);
//    }

    // Get방식
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user() {
        // 데이터 처리
        return "requestData";
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public void post1(@RequestBody TestDTO requestData){
        // get과 다르게 객체를 사용하여 받아와도 RequestBody를 입력해줘야한다.
        System.out.println(requestData);
    }



    @RequestMapping(value = "/put3/{userId}", method = RequestMethod.PUT)
    public TestDTO put3(@RequestBody TestDTO requestDto, @PathVariable(name="userId") Long id){
        System.out.println(requestDto);
        return requestDto;
    }


//    @PutMapping("/put1")
//    public void put1(@RequestBody TestDTO requestDto){
//        System.out.println(requestDto);
//
//    }


    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String userId, @RequestParam String name) {

        System.out.println(userId);
        System.out.println(name);
    }
}

package com.example.test.controller;

import com.example.test.dto.TestDTO;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Map;

@RestController
@RequestMapping("/api/get")
@EnableSwagger2
public class GetApiController {

    @GetMapping(path = "query-param03")
    public String queryParam03(TestDTO userRequest){
        // 이전 방식과 다르게 @RequestParam을 붙이지 않는다.
        // Spring boot에서는 parameter로 객체가 들어오면
        // "?user=steve&email=steve@gmail.com&age=30"와 같은
        // query parameter에 있는 객체들을 spring boot에서 판단한다.
        // 그리고 key에 위치한 값들을 객체의 변수의 이름과 매칭을 해준다.
        System.out.println(userRequest.getName());
        System.out.println(userRequest.getCar());

        return userRequest.toString();
    }

  }



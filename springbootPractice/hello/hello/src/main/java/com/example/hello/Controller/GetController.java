package com.example.hello.Controller;

import com.example.hello.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public  String getHello(){
        return "Hello world";
    }


    @GetMapping(value = "/name")
    public  String getName(){
        return "haeran";
    }

    @GetMapping(value = "/variable1/{variable}")
    public  String getVariable1(@PathVariable String variable){
        return variable;
    }

    @GetMapping(value = "/variable2/{variable}")
    public  String getVariable2(@PathVariable("variable") String var){
        return var;
    }

    //http://localhost:9090/api/v1/get-api/request1?name=value&email=value2&organization=value3
    @GetMapping(value = "/request1")
    public  String getRequestParam1(@RequestParam String name, @RequestParam String email, @RequestParam String organization){
        return name+""+email+""+organization;
    }

    //http://localhost:9090/api/v1/get-api/request2?key1=value&key2=value2
    @GetMapping(value = "/request2")
    public  String getRequestParam2(@RequestParam Map<String, String> param){
        StringBuilder sb = new StringBuilder();
        param.entrySet().forEach(map->{
            sb.append(map.getKey()+":" + map.getValue()+"\n");
        });
        return sb.toString();
    }
    //http://localhost:9090/api/v1/get-api/request3?name=value&email=value2&organization=value3
    @GetMapping(value = "/request3")
    public String getRequestParam3(MemberDto memberDto){
        return memberDto.toString();
    }

}

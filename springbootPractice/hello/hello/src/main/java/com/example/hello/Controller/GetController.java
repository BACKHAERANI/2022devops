package com.example.hello.Controller;

import org.springframework.web.bind.annotation.*;

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

    @GetMapping(value = "/request1")
    public  String getRequestParam1(@RequestParam String name, @RequestParam String email, @RequestParam String organization){
        return name+""+email+""+organization;
    }

}

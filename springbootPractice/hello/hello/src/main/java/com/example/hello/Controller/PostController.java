package com.example.hello.Controller;

import com.example.hello.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    @RequestMapping(value = "/domain", method = RequestMethod.POST)
    public String postExample(){
        return "Hello Post API";
    }

    @RequestMapping(value = "/member", method = RequestMethod.POST)
    public String postMember(@RequestBody Map<String, Object> postData){

        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map->{sb.append(map.getKey()+":"+map.getValue()+"\n");});

        return sb.toString();
    }


    @PostMapping(value = "/member2")
    public String postMemberDto(@RequestBody MemberDto memberDto){
        return memberDto.toString();
    }



}

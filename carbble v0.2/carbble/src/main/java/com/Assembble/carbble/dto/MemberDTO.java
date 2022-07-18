package com.Assembble.carbble.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.bind.annotation.RequestBody;
@Setter
@Getter
@ToString
public class MemberDTO {

    private Integer id;
    private String password;
}

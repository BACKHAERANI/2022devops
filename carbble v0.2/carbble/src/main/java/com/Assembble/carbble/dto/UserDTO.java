package com.Assembble.carbble.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class UserDTO {

    @NotNull(message = "아이디를 입력해주세요")
    private Integer userId;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private  String password;

    @NotEmpty(message = "이름을 입력해주세요.")
    private String username;

    private  String partname;

    private String position;

    private String telephone;

    @NotNull(message = "권한을 입력해주세요")
    private Integer authority;

}

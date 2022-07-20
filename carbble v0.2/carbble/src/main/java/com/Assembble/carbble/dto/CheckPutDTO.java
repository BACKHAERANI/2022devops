package com.Assembble.carbble.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Getter
@Setter
@ToString
public class CheckPutDTO {

    private Date checkDate;

    private String checkContent;

    private int checkMileage;


}

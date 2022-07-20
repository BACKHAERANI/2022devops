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
public class CheckDTO {

    private  int checkId;
    @NotNull
    private Date checkDate;
    @NotEmpty
    private String checkContent;
    @NotNull
    private int checkMileage;
    @NotNull
    private int carId;



}

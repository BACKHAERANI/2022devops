package com.Assembble.carbble.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
public class ReturnDTO {

    private int return_id;
    @NotEmpty
    private  String destination;
    @NotNull
    private int distance;
    @NotNull
    private int mileage;
    @NotEmpty
    private String parking;
    @NotNull
    private int refuelingAmount;
    @NotNull
    private Date returnDate;
    @NotNull
    private int reservationId;
    @NotEmpty
    private String damage;
    @NotEmpty
    private String lighting;
    @NotEmpty
    private String dashboard;
    @NotEmpty
    private String besides;


}

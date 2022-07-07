package com.Assembble.carbble.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
@Getter
@Setter
@ToString
public class IReturnDTO {

    private int car_id;
    private String destination;
    private int mileage;
    private String parking;
    private int refueling_amount;
    private String damage;
    private String lighting;
    private String dashboard;
    private String besides;

}

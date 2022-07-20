package com.Assembble.carbble.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BReturnDTO {

    private int car_id;
    private String destination;
    private int mileage;
    private String parking;
    private int refueling_amount;
    private Boolean refuelingCheck;
    private String damage;
    private String lighting;
    private String dashboard;
    private String besides;

}

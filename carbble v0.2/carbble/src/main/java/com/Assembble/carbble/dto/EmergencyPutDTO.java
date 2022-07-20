package com.Assembble.carbble.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class EmergencyPutDTO {

    private Date emergencyDate;
    private String emergencyContent;
    private int carId;
}

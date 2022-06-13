package com.Assembble.carbble.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class ReservationDTO {
    private int id;
    private String username;
    private String car;
    private Date startdate;
    private Date enddate;
    private Integer timerange;
    private Integer purpose;
    private String purposeDetail;

}

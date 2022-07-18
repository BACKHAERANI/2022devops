package com.Assembble.carbble.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class ReservationDTO {

    private Integer reservationId;

    private Date startdate;

    private Date enddate;

    private Integer purpose;

    private String purposeDetail;

    private Integer rentalState;

    private Date reservationDate;

    private Integer carId;

    private Integer userId;

    private Integer returnId;


}

package com.Assembble.carbble.dto;

import io.swagger.models.auth.In;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


@Getter
@Setter
@ToString
public class ReservationPutDTO {

private Integer reservationId;
private Date startdate;
private Date enddate;
private Integer purpose;
private String purposeDetail;
private Date reservationDate;
private Date returnDate;
private String username;
private String carName;
private Integer refueling_amount;
private String destination;
private Integer mileage;
private String parking;
private String damage;
private String lighting;
private String dashboard;
private String besides;
private Integer distance;



        }

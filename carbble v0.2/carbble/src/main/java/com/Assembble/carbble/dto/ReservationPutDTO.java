package com.Assembble.carbble.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;


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
private Integer rentalState;
private Integer userId;
private Integer carId;


private Date returnDate;
private Integer refueling_amount;
private String destination;
private Integer mileage;
private Integer distance;
private String parking;
private String damage;
private String lighting;
private String dashboard;
private String besides;






        }

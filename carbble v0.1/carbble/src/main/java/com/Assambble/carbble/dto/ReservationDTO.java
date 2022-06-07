package com.Assambble.carbble.dto;

import com.Assambble.carbble.model.entity.Reservation;
import lombok.*;

import java.security.PrivateKey;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReservationDTO {

    private Integer id;
    private String username;
    private String car;
    private Date startdate;
    private Date enddate;
    private Integer timerange;
    private Integer purpose;
    private String purposeDetail;

    public  static  ReservationDTO fromEntity(Reservation reservation) {
        return ReservationDTO.builder()
                .id(reservation.getId())
                .username(reservation.getUsername())
                .car(reservation.getCar())
                .startdate(reservation.getStartdate())
                .enddate(reservation.getEnddate())
                .timerange(reservation.getTimerange())
                .purpose(reservation.getPurpose())
                .purposeDetail(reservation.getPurposeDetail())
                .build();
    }

}

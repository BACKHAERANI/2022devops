package com.Assambble.carbble.dto;


import lombok.*;
import javax.validation.constraints.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ReservationDTO {

    private Integer id;

    @NotEmpty(message = "이름은 필수 입력값입니다.")
    private String username;

    @NotEmpty(message = "차종을 입력해주세요.")
    private String car;

    @NotNull(message = "시작일을 입력해주세요.")
    private Date startdate;

    @Future
    @NotNull(message = "종료일을 입력해주세요.")
    private Date enddate;

    @NotNull(message = "오전/오후/종일 중에 선택해주세요.")
    @Max(value = 3)
    private Integer timerange;

    @NotNull(message = "이용목적을 선택해주세요.")
    @Max(value = 2)
    private Integer purpose;

    @NotEmpty(message = "상세한 이용목적을 입력해주세요.")
    private String purposeDetail;


    @Override
    public String toString(){
        return "Reservation{"+
                "id='" + id + '\'' +
                "username='" + username + '\'' +
                ", car=" + car +
                ", startdate='" + startdate + '\'' +
                ", enddate='" + enddate + '\'' +
                ", timerange='" + timerange + '\'' +
                ", purpose='" + purpose + '\'' +
                ", purposeDetail='" + purposeDetail + '\'' +
                '}';
    }


}




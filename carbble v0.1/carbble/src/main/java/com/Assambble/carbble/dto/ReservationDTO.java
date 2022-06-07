package com.Assambble.carbble.dto;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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
    private Integer timerange;

    @NotNull(message = "이용목적을 선택해주세요.")
    private Integer purpose;

    @NotEmpty(message = "상세한 이용목적을 입력해주세요.")
    private String purposeDetail;


}

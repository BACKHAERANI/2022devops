package com.Assembble.carbble.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Getter
@Setter
@ToString
public class BReservationDTO {



    @NotNull(message = "시작일을 입력해주세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private Date startdate;


    @NotNull(message = "종료일을 입력해주세요.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private Date enddate;


    @NotNull(message = "이용목적을 선택해주세요.")
    @Max(value = 1)
    private Integer purpose;

    @NotEmpty(message = "상세한 이용목적을 입력해주세요.")
    private String purposeDetail;

    private Integer carId;

    private Integer userId;




}

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
public class PutDTO {

    @NotNull(message = "시작일을 입력해주세요.")
    private Date startdate;

    @Future
    @NotNull(message = "종료일을 입력해주세요.")
    private Date enddate;
}

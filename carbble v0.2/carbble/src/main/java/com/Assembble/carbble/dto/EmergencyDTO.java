package com.Assembble.carbble.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Date;


@Getter
@Setter
@ToString
public class EmergencyDTO {

    private int emergencyId;
    @NotNull
    private Date emergencyDate;
    @NotEmpty
    private String emergencyContent;
    @NotNull
    private int carId;

}

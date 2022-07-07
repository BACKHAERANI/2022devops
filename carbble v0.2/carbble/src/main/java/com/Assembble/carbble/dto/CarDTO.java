package com.Assembble.carbble.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;



import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@ToString
public class CarDTO {

    private int carId;
    @NotEmpty
    private String carName;
    @NotEmpty
    private String carNum;
    @NotEmpty
    private String carYear;

    private int carMileage;
    @NotNull
    private Date acquisitionDate;
    @NotEmpty
    private String admin;
    @NotNull
    private Boolean useStatus;

}

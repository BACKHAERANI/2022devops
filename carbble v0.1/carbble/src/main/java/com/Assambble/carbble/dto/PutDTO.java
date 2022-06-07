package com.Assambble.carbble.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class PutDTO {

    private Date startdate;
    private Date enddate;
}

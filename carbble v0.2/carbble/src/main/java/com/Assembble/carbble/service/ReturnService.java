package com.Assembble.carbble.service;


import com.Assembble.carbble.dto.CarDTO;
import com.Assembble.carbble.dto.ReservationPutDTO;
import com.Assembble.carbble.dto.ReturnDTO;

import java.util.Date;
import java.util.List;

public interface ReturnService {

    List<ReturnDTO> getReturn();

    int postReturn(ReturnDTO dto);





}

package com.Assembble.carbble.service;


import com.Assembble.carbble.dao.CarDAOImpl;
import com.Assembble.carbble.dao.ReservationDAOImpl;
import com.Assembble.carbble.dao.ReturnDAOImpl;
import com.Assembble.carbble.dto.CarDTO;
import com.Assembble.carbble.dto.ReservationPutDTO;
import com.Assembble.carbble.dto.ReturnDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;


@Repository
public class ReturnServiceImpl implements ReturnService {

    @Autowired
    ReturnDAOImpl dao;

    @Override
    public List<ReturnDTO> getReturn()
    {
        return dao.select();
    }

    @Override
    public int postReturn(ReturnDTO dto){
        return dao.insertReturn(dto);
    }








}




package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.ReturnDTO;

import java.util.List;

public interface ReturnDAO {
    List<ReturnDTO> select();



    int insertReturn(ReturnDTO dto);
}


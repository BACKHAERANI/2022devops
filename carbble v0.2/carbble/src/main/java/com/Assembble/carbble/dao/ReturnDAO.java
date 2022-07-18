package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.ReturnDTO;

import java.util.List;

public interface ReturnDAO {
    List<ReturnDTO> select();


    ReturnDTO selectReturnId(int reservation_id);

    List<ReturnDTO> selectReturnByReturnIdList(List<Integer> list);

    int insertReturn(ReturnDTO dto);
}


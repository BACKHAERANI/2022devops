package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.*;

import java.util.Date;
import java.util.List;

public interface ReturnDAO {
    List<ReturnDTO> select();


    ReturnDTO selectReturnId(int reservation_id);

    List<ReturnDTO> selectReturnByReturnIdList(List<Integer> list);

    int insertReturn(ReturnDTO dto);

    List<SumRefuelingDTO> sumRefueling(PutDateDTO dto);

    List<CountRefuelingDTO> countRefueling(PutDateDTO dto);
}


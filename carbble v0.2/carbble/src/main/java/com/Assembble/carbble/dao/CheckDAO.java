package com.Assembble.carbble.dao;

import com.Assembble.carbble.dto.CheckDTO;
import com.Assembble.carbble.dto.CheckPutDTO;

import java.sql.Date;
import java.util.List;

public interface CheckDAO {
    List<CheckDTO> selectCheck(Date startdate, Date enddate);

    int insertCheck(CheckDTO dto);

    int updateCheck(int check_id, CheckPutDTO dto);

    int deleteCheck(int check_id);
}

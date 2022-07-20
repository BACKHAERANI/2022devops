package com.Assembble.carbble.controller;

import com.Assembble.carbble.dto.CheckDTO;
import com.Assembble.carbble.dto.CheckPutDTO;
import com.Assembble.carbble.dto.PutDateDTO;
import com.Assembble.carbble.service.DatabaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@EnableSwagger2
@RestController
@Validated
public class CheckController {

    @Autowired
    DatabaseServiceImpl service;


    @RequestMapping(value = "/check", method = RequestMethod.PUT)
    public ResponseEntity<List<CheckDTO>> putCheckList(@RequestBody PutDateDTO dto)
    {

        List<CheckDTO> checkDTOList = service.selectCheck(dto.getStartdate(), dto.getEnddate());

        if(checkDTOList != null)
        {
            log.info("checklist:[{}]", checkDTOList);
            return new ResponseEntity<>(checkDTOList,HttpStatus.OK);
        }
        else
        {
            log.error("checklist IS NULL");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<String> addCheck(@Valid @RequestBody CheckDTO dto)
    {
        log.info("post check CONTENT:[{}]",dto.toString());

        if(service.insertCheck(dto) > 0)
        {
            log.info("post check SUCCESS");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else
        {
            log.error("post check FAIL:[점검 내역을 저장할 수 없음]");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/check/{checkId}", method = RequestMethod.PUT)
    public ResponseEntity<String> modifyCheck(@PathVariable("checkId") int check_id, @RequestBody CheckPutDTO dto)
    {
        log.info("put check ...CONTENT :[{}]", dto.toString());

        if(service.updateCheck(check_id,dto) > 0){
            log.info("put check SUCCESS");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            log.error("put check FAIL:[점검내역 수정 실패]");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/check/{checkId}", method = RequestMethod.DELETE)
    public ResponseEntity<String> removeCheck(@PathVariable("checkId") int check_id)
    {
        log.info("delete check_id :[{}]", check_id);

        if(service.deleteCheck(check_id) > 0)
        {
            log.info("delete check:[SUCCESS]");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
        {
            log.error("delete check:[FAIL]");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}

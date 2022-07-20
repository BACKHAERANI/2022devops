package com.Assembble.carbble.controller;

import com.Assembble.carbble.dto.EmergencyDTO;
import com.Assembble.carbble.dto.EmergencyPutDTO;
import com.Assembble.carbble.dto.PutDateDTO;
import com.Assembble.carbble.service.DatabaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.Date;
import java.util.List;

@Slf4j
@EnableSwagger2
@RestController
public class EmergencyController {

    @Autowired
    DatabaseServiceImpl service;


    @RequestMapping(value = "/emergency", method = RequestMethod.PUT)
    public ResponseEntity<List<EmergencyDTO>> getEmergencyList(@RequestBody PutDateDTO dto)
    {
        List<EmergencyDTO> emergencyDTOList = service.selectEmergency(dto.getStartdate(), dto.getEnddate());

        if(emergencyDTOList != null)
        {
            log.info("emergencylist:[{}]", emergencyDTOList);
            return new ResponseEntity<>(emergencyDTOList,HttpStatus.OK);
        }
        else
        {
            log.error("emergency IS NULL");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }



    @RequestMapping(value = "/emergency", method = RequestMethod.POST)
    public ResponseEntity<EmergencyDTO> addEmergency(@RequestBody EmergencyDTO dto)
    {
        log.info("post emergency ...CONTENT:[{}]", dto.toString());

        if(service.insertEmergency(dto) >0)
        {
            log.info("post emergency SUCCESS");
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else
        {
            log.error("post emergency FAIL :[잘못된 요청입니다.]");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/emergency/{emergencyId}", method = RequestMethod.PUT)
    public ResponseEntity<String> modifyEmergency(@PathVariable("emergencyId") int emergency_id, @RequestBody EmergencyPutDTO dto)
    {
        log.info("put emergency CONTENT:[{}]", dto.toString());


        if(service.updateEmergency(emergency_id, dto) >0)
        {
            log.info("put emergency SUCCESS");
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
        {
            log.error("post emergency FAIL :[잘못된 요청입니다.]");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}

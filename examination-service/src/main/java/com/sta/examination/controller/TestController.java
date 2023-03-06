package com.sta.examination.controller;

import com.sta.academics.service.ITestService;
import com.sta.examination.dto.TestDTO;
import com.sta.examination.exception.TestServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/examination")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired
    ITestService testService;

    @GetMapping(value = "/status")
    public String status() {
        return " Examination   Service is Running...";
    }
    @PostMapping(value = "/addExaminationDetails")
    public ResponseEntity addFeeDetails(@RequestBody TestDTO testDTO) {

        try{
            log.info("Creating  Details...");
            testService.addFeeDetails(testDTO);
            return new ResponseEntity("Academics Created", HttpStatus.OK);
        }catch (TestServiceException feeServiceException){
            log.error("Error While Creating Fee Details {} :: ",feeServiceException.getMessage());
            return new ResponseEntity("Fee Details Creation Failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

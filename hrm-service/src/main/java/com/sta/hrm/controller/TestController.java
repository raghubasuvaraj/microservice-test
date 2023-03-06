package com.sta.hrm.controller;

import com.sta.hrm.dto.TestDTO;
import com.sta.hrm.exception.TestServiceException;
import com.sta.hrm.service.ITestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hrm")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired
    ITestService feeService;

    @GetMapping(value = "/status")
    public String status() {
        return "HRM Service is Running...";
    }
    @PostMapping(value = "/addFeeDetails")
    public ResponseEntity addFeeDetails(@RequestBody TestDTO testDTO) {

        try{
            log.info("Creating Fee Details...");
            feeService.addFeeDetails(testDTO);
            return new ResponseEntity("Fee Details Created", HttpStatus.OK);
        }catch (TestServiceException feeServiceException){
            log.error("Error While Creating Fee Details {} :: ",feeServiceException.getMessage());
            return new ResponseEntity("Fee Details Creation Failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

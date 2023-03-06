package com.sta.document.controller;

import com.sta.document.dto.TestDTO;
import com.sta.document.exception.TestServiceException;
import com.sta.document.service.ITestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/document")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired
    ITestService testService;

    @GetMapping(value = "/status")
    public String status() {
        return " Document   Service is Running...";
    }
    @PostMapping(value = "/addDocumentDetails")
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

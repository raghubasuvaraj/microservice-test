package com.sta.payment.controller;

import com.sta.payment.dto.TestDTO;
import com.sta.payment.exception.TestServiceException;
import com.sta.payment.service.ITestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired
    ITestService testService;

    @GetMapping(value = "/status")
    public String status() {
        return " Payment   Service is Running...";
    }
    @PostMapping(value = "/addPaymentDetails")
    public ResponseEntity addFeeDetails(@RequestBody TestDTO testDTO) {

        try{
            log.info("Creating  Details...");
            testService.addFeeDetails(testDTO);
            return new ResponseEntity("Payment Created", HttpStatus.OK);
        }catch (TestServiceException feeServiceException){
            log.error("Error While Creating  Details {} :: ",feeServiceException.getMessage());
            return new ResponseEntity(" Details Creation Failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

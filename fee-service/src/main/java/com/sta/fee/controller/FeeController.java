package com.sta.fee.controller;

import com.sta.fee.dto.FeeDTO;
import com.sta.fee.exception.FeeServiceException;
import com.sta.fee.service.IFeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fee")
public class FeeController {

    private static final Logger log = LoggerFactory.getLogger(FeeController.class);
    @Autowired
    IFeeService feeService;

    @GetMapping(value = "/status")
    public String status() {
        return "Fee Service is Running...";
    }
    @PostMapping(value = "/addFeeDetails")
    public ResponseEntity addFeeDetails(@RequestBody FeeDTO feeDetails) {
        try{
            log.info("Creating Fee Details...");
            feeService.addFeeDetails(feeDetails);
            return new ResponseEntity("Fee Details Created", HttpStatus.OK);
        }catch (FeeServiceException feeServiceException){
            log.error("Error While Creating Fee Details {} :: ",feeServiceException.getMessage());
            return new ResponseEntity("Fee Details Creation Failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

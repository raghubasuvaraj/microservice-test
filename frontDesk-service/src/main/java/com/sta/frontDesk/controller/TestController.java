package com.sta.frontDesk.controller;



import com.sta.frontDesk.dto.TestDTO;
import com.sta.frontDesk.exception.TestServiceException;
import com.sta.frontDesk.service.ITestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/frontDesk")
public class TestController {

    private static final Logger log = LoggerFactory.getLogger(TestController.class);
    @Autowired
    ITestService testService;

    @GetMapping(value = "/status")
    public String status() {
        return "FRONT DESK   Service is Running...";
    }
    @PostMapping(value = "/addFeeDetails")
    public ResponseEntity addFeeDetails(@RequestBody TestDTO testDTO) {

        try{
            log.info("Creating Fee Details...");
            testService.addFeeDetails(testDTO);
            return new ResponseEntity("FRONT DESK Created", HttpStatus.OK);
        }catch (TestServiceException feeServiceException){
            log.error("Error While Creating Fee Details {} :: ",feeServiceException.getMessage());
            return new ResponseEntity("Fee Details Creation Failed",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

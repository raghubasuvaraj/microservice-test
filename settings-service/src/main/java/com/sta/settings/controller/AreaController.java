package com.sta.settings.controller;

import com.sta.settings.dto.request.AreaRequest;
import com.sta.settings.dto.response.AreaResponse;
import com.sta.settings.service.IAreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/area")
@Slf4j
public class AreaController {

    @Autowired
    IAreaService areaService;

    @PostMapping()
    public ResponseEntity saveAreaForBranch(@RequestBody List<AreaRequest> branchAreaRequestList) {
        List<AreaResponse> responseDto = areaService.saveAreaForBranch(branchAreaRequestList);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}

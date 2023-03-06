package com.sta.settings.controller;

import com.sta.settings.dto.request.BranchAreaRequest;
import com.sta.settings.dto.response.BranchAreaResponse;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.service.IBranchAreaService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/branchArea")
public class BranchAreaController {

    @Autowired
    IBranchAreaService branchAreaService;

    @PostMapping()
    @Operation(summary = "Create or Update Area")
    public ResponseEntity<List<BranchAreaResponse>> createOrUpdateAreaToBranch(@Valid @RequestBody List<BranchAreaRequest> branchAreaRequestList) {
        List<BranchAreaResponse> response = branchAreaService.saveOrUpdateBranchArea(branchAreaRequestList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete BranchArea")
    public ResponseEntity<BranchAreaResponse> deleteBranchArea(@PathVariable String id){
        branchAreaService.deleteBranchArea(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{branchId}")
    @Operation(summary = "Get All BranchAreas")
    public ResponseEntity<List<BranchAreaResponse>> getAllBranchAreas(@PathVariable String branchId) {
        try{
            List<BranchAreaResponse> responseDto = branchAreaService.getAllBranchAreas(branchId);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }catch (SettingsException settingsException){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/branchArea/{id}")
    @Operation(summary = "Get  BranchArea")
    public ResponseEntity<BranchAreaResponse> getBranchAreaById(@PathVariable String id) {
        return ResponseEntity.status(HttpStatus.OK).body(branchAreaService.getBranchAreasById(id));
    }
}

package com.sta.settings.controller;

import com.sta.settings.dto.request.MasterFieldRequest;
import com.sta.settings.dto.response.MasterFieldResponse;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.service.IMasterFieldService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/masterField")
@Slf4j
public class MasterFieldController {
    @Autowired
    private IMasterFieldService IMasterFieldService;

    @PostMapping()
    @Operation(summary = "Update Master Field")
    public ResponseEntity<MasterFieldResponse> updateMasterField(@Valid @RequestBody List<MasterFieldRequest> masterFieldRequestsList) {
        MasterFieldResponse response = IMasterFieldService.saveOrUpdateMasterField(masterFieldRequestsList);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
    @GetMapping("/{fieldId}")
    @Operation(summary = "Get Master Field")
    public ResponseEntity<MasterFieldResponse> getMasterField(@PathVariable String fieldId) {
            MasterFieldResponse responseDto = IMasterFieldService.getMasterField(fieldId);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @GetMapping()
    @Operation(summary = "Get All Master Fields")
    public ResponseEntity<List<MasterFieldResponse>> getAllMasterField() {
        try{
            List<MasterFieldResponse> responseDto = IMasterFieldService.getAllMasterField();
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }catch (SettingsException settingsException){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
     }
    @DeleteMapping("/{fieldId}")
    @Operation(summary = "Delete Master Field")
    public ResponseEntity<MasterFieldResponse> deleteMasterField(@PathVariable String fieldId) {
        IMasterFieldService.deleteMasterField(fieldId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}

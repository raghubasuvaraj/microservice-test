package com.sta.settings.controller;

import com.sta.settings.dto.InstitutesSystemInfoDto;
import com.sta.settings.service.IInstitutesSystemInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/institutesysteminfo")
public class InstitutesSystemInfoController {

    @Autowired
    private IInstitutesSystemInfoService IInstitutesSystemInfoService;

    @PostMapping()
    public ResponseEntity<InstitutesSystemInfoDto> saveInstituteSystemInfo(@Valid @RequestBody InstitutesSystemInfoDto institutesSystemInfoDto) {
        InstitutesSystemInfoDto responseDto = IInstitutesSystemInfoService.saveInstituteSystemInfo(institutesSystemInfoDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @PutMapping()
    public ResponseEntity<InstitutesSystemInfoDto> updateInstituteSystemInfo(@Valid @RequestBody InstitutesSystemInfoDto institutesSystemInfoDto, HttpServletRequest request) {
        InstitutesSystemInfoDto responseDto = IInstitutesSystemInfoService.updateInstituteSystemInfo(institutesSystemInfoDto, request);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{instituteId}")
    public ResponseEntity<InstitutesSystemInfoDto> getInstituteSystemInfoByInstituteId(@PathVariable("instituteId") String instituteId) {
        return new ResponseEntity<>(IInstitutesSystemInfoService.getInstituteSystemInfoByInstituteId(instituteId), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<InstitutesSystemInfoDto>> getAllInstituteSystemInfo() {
        List<InstitutesSystemInfoDto> responseDto = IInstitutesSystemInfoService.getAllInstituteSystemInfo();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }
    @DeleteMapping("/{instituteId}")
    public ResponseEntity<String> deleteInstituteSystemInfoByInstituteId(@PathVariable("instituteId") String instituteId) {
       return IInstitutesSystemInfoService.deleteInstituteSystemInfoByInstituteId(instituteId);
    }

}

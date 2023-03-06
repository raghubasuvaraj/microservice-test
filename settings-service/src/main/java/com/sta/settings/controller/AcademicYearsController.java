package com.sta.settings.controller;

import com.sta.settings.dto.AcademicYearDto;
import com.sta.settings.entities.InstituteAcademicYearEntity;
import com.sta.settings.service.IAcademicYear;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/academic")
public class AcademicYearsController {

    @Autowired
    private IAcademicYear IAcademicYear;

    @PostMapping()
    public ResponseEntity<AcademicYearDto> saveAcademicYear(@Valid @RequestBody AcademicYearDto academicYearDto) {
        log.info("Saving academic year for instituteID - {} ::", academicYearDto.getInstituteId());
        return new ResponseEntity<>(IAcademicYear.saveAcademicYear(academicYearDto), HttpStatus.CREATED);
    }
    @PutMapping()
    public ResponseEntity<AcademicYearDto> updateAcademicYear(@Valid @RequestBody AcademicYearDto academicYearDto) {
        log.info("Updating academic year for id - {} ::", academicYearDto.getId());
        return new ResponseEntity<>(IAcademicYear.updateAcademicYear(academicYearDto), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<InstituteAcademicYearEntity> getAcademicYear(@PathVariable("id") String id) {
        log.info("fetching academic year for id - {} ::", id);
        return new ResponseEntity<>(IAcademicYear.getAcademicYear(id), HttpStatus.OK);
    }
    @GetMapping("/institute/{institute_id}")
    public ResponseEntity<List<InstituteAcademicYearEntity>> getAllAcademicYear(@PathVariable("institute_id") String instituteId) {
        log.info("fetching academic year for institute id - {} ::", instituteId);
        return new ResponseEntity<>(IAcademicYear.getAllAcademicYears(instituteId), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAcademicYear(@PathVariable("id") String id) {
        log.info("deleting academic year by id - {} ::", id);
        return IAcademicYear.deleteAcademicYear(id);
    }
    @GetMapping("/current-academicyear")
    public ResponseEntity<InstituteAcademicYearEntity> getCurrentAcademicYear() {
        return new ResponseEntity<>(IAcademicYear.getCurrentAcademicYear(), HttpStatus.OK);
    }
}

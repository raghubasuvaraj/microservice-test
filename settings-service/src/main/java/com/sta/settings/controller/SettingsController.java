package com.sta.settings.controller;

import com.sta.settings.dto.request.CurriculumRequest;
import com.sta.settings.dto.request.CurriculumRequestList;
import com.sta.settings.entities.*;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.service.ISettingsService;
import com.sta.settings.utility.ErrorCodesConstants;
import com.sta.settings.utility.MessageConstants;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/settings")
public class SettingsController {

    @Autowired
    private ISettingsService settingsService;

    @GetMapping("/class-groups")
    public ResponseEntity<List<ClassGroupsEntity>> getAllClassGroups() {
        return ResponseEntity.status(HttpStatus.OK).body(settingsService.getAllClassGroups());
    }

    @PostMapping("/curriculums")
    public ResponseEntity<List<InstituteCurriculumEntity>> createCurriculums(@Valid @RequestBody CurriculumRequestList request,
                                                             @RequestParam(value = "instituteId", required = true) String instituteId) throws SettingsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(settingsService.createCurriculums(request, instituteId));
    }

    @GetMapping("/curriculums")
    public ResponseEntity<List<InstituteCurriculumEntity>> getAllCurriculums(@RequestParam(value = "instituteId", required = true) String instituteId,
                                                               @RequestParam(value = "curriculumName", required = false) String curriculumName) {
        return ResponseEntity.ok().body(settingsService.getAllCurriculums(instituteId, curriculumName));
    }

    @PutMapping("/curriculums")
    public ResponseEntity<InstituteCurriculumEntity> updateCurriculum(
            @RequestParam(value = "curriculumId", required = true) String curriculumId,
            @RequestParam(value = "instituteId", required = false) String instituteId,
            @Valid @RequestBody CurriculumRequest request) {
        return ResponseEntity.ok().body(settingsService.updateCurriculum(curriculumId, instituteId, request));
    }

    @DeleteMapping("/curriculums/{instituteCurriculumId}")
    public ResponseEntity<?> deleteCurriculum(@PathVariable String instituteCurriculumId,
                                              @RequestParam(value = "instituteId", required = false) String instituteId) {
        this.settingsService.deleteCurriculum(instituteCurriculumId, instituteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("/branch-curriculums")
    public ResponseEntity<List<BranchCurriculumEntity>> createBranchCurriculums(@Valid @RequestBody List <String> curriculumIds,
                                                                             @RequestParam(value = "branchId", required = true) String branchId) throws SettingsException {
        return ResponseEntity.status(HttpStatus.CREATED).body(settingsService.createBranchCurriculums(curriculumIds, branchId));
    }

    @GetMapping("/branch-curriculums")
    public ResponseEntity<List<BranchCurriculumEntity>> getBranchCurriculums(@RequestParam(value = "branchId", required = true) String branchId,
                                                                             @RequestParam(value = "curriculumName", required = false) String curriculumName) {
        return ResponseEntity.ok().body(settingsService.getBranchCurriculums(branchId, curriculumName));
    }

    @DeleteMapping("/branch-curriculums/{branchCurriculumId}")
    public ResponseEntity<?> deleteBranchCurriculum(@PathVariable String branchCurriculumId,
                                              @RequestParam(value = "branchId", required = true) String branchId) {
        this.settingsService.deleteBranchCurriculum(branchCurriculumId, branchId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

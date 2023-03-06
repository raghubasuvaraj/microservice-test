package com.sta.settings.controller;

import com.sta.dc.common.entity.academic.MasterAcademicClassesEntity;
import com.sta.settings.entities.*;
import com.sta.settings.service.ISettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/master")
public class MasterController {
    @Autowired
    private ISettingsService settingsService;


    @GetMapping("/master-fields")
    public ResponseEntity<List<MasterFieldsDataEntity>> getAllMasterFieldsByCategoryName(@RequestParam(value = "categoryName", required = true) String categoryName,
                                                                                         @RequestParam(value = "name", required = false) String name) {
        return ResponseEntity.status(HttpStatus.OK).body(settingsService.getAllMasterFields(categoryName,name));
    }

    @GetMapping("/languages")
    public ResponseEntity<List<LanguageEntity>> getAllLanguages(@RequestParam(value = "languageName", required = false)String name) {
        return ResponseEntity.status(HttpStatus.OK).body(settingsService.getAllLanguages(name));
    }

    @GetMapping("/modules")
    public ResponseEntity<List<MasterModules>>getAllMasterModules(@RequestParam(value = "moduleName", required = false)String name) {
        return ResponseEntity.status(HttpStatus.OK).body(settingsService.getAllMasterModules(name));
    }

    @GetMapping("/branch-types")
    public ResponseEntity<List<MasterBranchTypeEntity>> getAllBranchTypes(@RequestParam(value = "branchTypeName", required = false) String branchTypeName) {
        return ResponseEntity.status(HttpStatus.OK).body(settingsService.getAllBranchTypes(branchTypeName));
    }

    @GetMapping("/forms")
    public ResponseEntity<List<MasterFormsEntity>> getAllMasterForms(@RequestParam(value = "formName", required = false) String formName) {
        return ResponseEntity.status(HttpStatus.OK).body(settingsService.getAllMasterForms(formName));
    }

    @GetMapping("/master-academicYear")
    public ResponseEntity<List<MasterAcademicYearEntity>> getAllMasterAcademicYear(@RequestParam(value = "name", required = false)String name) {
        return ResponseEntity.status(HttpStatus.OK).body(settingsService.getAllMasterAcademicYear(name));
    }

    @GetMapping("/master-termTypes")
    public ResponseEntity<List<MasterTermTypeEntity>> getAllMasterTermTypes(@RequestParam(value = "name", required = false)String name) {
        return ResponseEntity.status(HttpStatus.OK).body(settingsService.getAllMasterTermTypes(name));
    }

    @GetMapping("/master-departments")
    public ResponseEntity<List<MasterDepartmentsEntity>> getAllMasterDepartments(@RequestParam(value = "name", required = false)String name) {
        return ResponseEntity.status(HttpStatus.OK).body(settingsService.getAllMasterDepartments(name));
    }
    @GetMapping("/master-academicClasses")
    public ResponseEntity<List<MasterAcademicClassesEntity>> getAllMasterAcademicClasses(@RequestParam(value = "name", required = false)String name) {
        return ResponseEntity.status(HttpStatus.OK).body(settingsService.getAllMasterAcademicClasses(name));
    }
    @GetMapping("/master-roles")
    public ResponseEntity<List<MasterRoleEntity>> getAllMasterRoles(@RequestParam(value = "name", required = false)String name) {
        return ResponseEntity.status(HttpStatus.OK).body(settingsService.getAllMasterRoles(name));
    }
    @GetMapping("/master-academic-curriculum")
    public ResponseEntity<List<MasterAcademicCurriculumEntity>> getAllMasterAcademicCurriculum(@RequestParam(value = "name", required = false)String name) {
        return ResponseEntity.status(HttpStatus.OK).body(settingsService.getAllMasterAcademicCurriculum(name));
    }
    @GetMapping("/master-terms")
    public ResponseEntity<List<MasterTermEntity>> getAllMasterTerm(@RequestParam(value = "name", required = false)String name) {
        return ResponseEntity.status(HttpStatus.OK).body(settingsService.getAllMasterTerm(name));
    }
}

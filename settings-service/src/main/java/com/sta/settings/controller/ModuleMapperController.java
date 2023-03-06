package com.sta.settings.controller;

import com.sta.settings.dto.ModuleDto;
import com.sta.settings.dto.ModuleMapperDto;
import com.sta.settings.entities.BranchModuleEntity;
import com.sta.settings.service.IModuleMapperService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/branchmodule")
public class ModuleMapperController {

    @Autowired
    private IModuleMapperService IModuleMapperService;

    @PutMapping()
    public ResponseEntity<ModuleMapperDto> updateModule(@Valid @RequestBody ModuleMapperDto moduleMapperDto, HttpServletRequest request) {
        log.info("updating branch modules mapping for branchId - {} ::", moduleMapperDto.getBranchId());
        return new ResponseEntity<>(IModuleMapperService.updateModule(moduleMapperDto, request), HttpStatus.CREATED);
    }

    @GetMapping("/{branchId}")
    public ResponseEntity<List<BranchModuleEntity>> getModuleByBranchId(@PathVariable(value = "branchId") String branchId) {
        log.info("get all branch modules mapping for branchId - {} ::", branchId);
        return new ResponseEntity<>(IModuleMapperService.getModuleByBranchId(branchId), HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<List<BranchModuleEntity>> getAllModule() {
        log.info("get all branch modules mapping");
        return new ResponseEntity<>(IModuleMapperService.getAllModule(), HttpStatus.OK);
    }
}

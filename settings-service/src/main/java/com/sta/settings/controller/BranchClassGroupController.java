package com.sta.settings.controller;

import com.sta.dc.common.entity.academic.BranchClassGroupEntity;
import com.sta.settings.dto.request.AcademicClassRequest;
import com.sta.settings.dto.request.ListClassGroupRequest;
import com.sta.settings.dto.response.GroupWithClassResponse;
import com.sta.settings.dto.response.SectionWithClassResponse;
import com.sta.settings.service.IClassGroupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classGroup")
@Slf4j
public class BranchClassGroupController {

    @Autowired
    IClassGroupService IClassGroupService;

    @PostMapping
    public ResponseEntity<List<BranchClassGroupEntity>> saveBranchClassGroup(@RequestBody ListClassGroupRequest request,
                                                                             @RequestParam(value = "branchId", required = true) String branchId){
            return new ResponseEntity<>(IClassGroupService.saveClassGroup(request,branchId),HttpStatus.CREATED);
    }

    @PostMapping("/assign/{groupId}")
    public ResponseEntity<?> addStandardsToClassGroup(@RequestBody AcademicClassRequest request,
                                                      @PathVariable String groupId) {
        IClassGroupService.addStandardsToClassGroup(request, groupId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/remove/{groupId}")
    public ResponseEntity<?> removeStandardsFromClassGroup(@RequestBody AcademicClassRequest request,
                                                      @PathVariable String groupId) {
        IClassGroupService.removeStandardsFromClassGroup(request, groupId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping
    public ResponseEntity<List<GroupWithClassResponse>> getAllGroupsWithStandards() {
        List<GroupWithClassResponse> response = IClassGroupService.getAllGroupsWithStandards();
        return new ResponseEntity<>(response,
                (!CollectionUtils.isEmpty(response)) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }


}

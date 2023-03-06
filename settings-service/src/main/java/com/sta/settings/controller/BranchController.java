package com.sta.settings.controller;

import com.sta.settings.dto.request.BranchApproveRequest;
import com.sta.settings.dto.request.BranchRequest;
import com.sta.settings.dto.request.InstitutesApproveRequest;
import com.sta.settings.dto.request.RequestForMainBranch;
import com.sta.settings.dto.response.BranchResponse;
import com.sta.settings.dto.response.InstitutesResponse;
import com.sta.settings.entities.BranchInfoEntity;
import com.sta.settings.service.IBranchInfoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/branch")
@Slf4j
public class BranchController {

    @Autowired
    IBranchInfoService IBranchInfoService;

    @GetMapping("/{instituteId}")
    @Operation(summary = "Get All Branches by InstituteId")
    public ResponseEntity<List<BranchResponse>> getAllBranchesByInstituteName(@PathVariable("instituteId") String instituteId) {
            List<BranchResponse> branchResponseList = IBranchInfoService.getAllBranches(instituteId);
            return new ResponseEntity<>(branchResponseList, HttpStatus.OK);
    }

    @GetMapping("/branch/{branchId}")
    @Operation(summary = "Get All Branches by InstituteId")
    public ResponseEntity<BranchResponse> getAllBranchByBranchId(@PathVariable("branchId") String branchId) {
            BranchResponse branchResponse = IBranchInfoService.getBranchById(branchId);
            return new ResponseEntity<>(branchResponse, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<BranchResponse> saveBranch(@Valid  @RequestBody BranchRequest branchDto) {
            log.info("Branch Creation Started ...");
            BranchResponse responseDto = IBranchInfoService.saveBranch(branchDto);
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);

    }

    @PutMapping
    public ResponseEntity<BranchResponse> updateBranchDetails(@Valid @RequestBody BranchRequest branchRequest){

            log.info("Branch Updated Started ...");
            BranchResponse responseDto = IBranchInfoService.updateBranchDetails(branchRequest);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);

    }

    @DeleteMapping("/{branchId}")
    public ResponseEntity<String> deleteBranch(@PathVariable("branchId") String branchId){
            log.info("Delete Branch    Started ...");
            boolean isBranchDelete = IBranchInfoService.deleteBranch(branchId);
            log.info("Delete Branch Done ... ");
            return new ResponseEntity<>("Branch Deleted :" + isBranchDelete, HttpStatus.OK);

    }
    @PutMapping("/{instituteId}/{branchId}")
    public ResponseEntity<String> updateBranchToMainBranch(@PathVariable("instituteId") String instituteId,
                                                           @PathVariable("branchId") String branchId,
                                                           @Valid @RequestBody RequestForMainBranch request) {
        BranchInfoEntity branchInfoEntity = this.IBranchInfoService.updateBranchToMainBranch(instituteId, branchId, request);
        return new ResponseEntity<>(" The branch " + branchInfoEntity.getBranchName() +" is selected as a main branch.", HttpStatus.OK);
    }

    @PutMapping("/approve-or-reject")
    public ResponseEntity<BranchResponse> approveOrRejectInstitute(
            @Valid @RequestBody BranchApproveRequest request) {
        BranchResponse response = IBranchInfoService.approveOrRejectBranch(request);
        return new ResponseEntity<>(response,
                (!ObjectUtils.isEmpty(response)) ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

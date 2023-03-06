package com.sta.settings.controller;

import com.sta.settings.dto.request.ListTermRequestForBranch;
import com.sta.settings.dto.request.TermListRequest;
import com.sta.settings.dto.request.TermRequest;
import com.sta.settings.dto.request.TermRequestForBranch;
import com.sta.settings.entities.BranchTermEntity;
import com.sta.settings.entities.InstituteTermEntity;
import com.sta.settings.service.ITermsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/term-branch")
@Slf4j
public class TermsBranchController {

    @Autowired
    private ITermsService termsService;

    @PostMapping
    public ResponseEntity<?> createTermsForBranch(@Valid @RequestBody ListTermRequestForBranch request,
                                                     @RequestParam(value = "branchId", required = true) String branchId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(termsService.saveBranchTerm(request, branchId));
    }

    @GetMapping
    public ResponseEntity<List<BranchTermEntity>> getAllInstituteTerms(@RequestParam(value = "branchId", required = true) String branchId,
                                                                       @RequestParam(value = "termName", required = false) String termName) {
        return ResponseEntity.status(HttpStatus.OK).body(termsService.getAllBranchTerms(branchId,termName));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBranchTerm(@PathVariable String id,
                                                 @RequestParam(value = "branchId", required = true) String branchId) {
        this.termsService.deleteBranchTerm(id, branchId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/branch-term")
    public ResponseEntity<BranchTermEntity> updateBranchTerm(
            @Valid @RequestBody TermRequestForBranch request,
            @RequestParam(value = "termId", required = true) String termId,
            @RequestParam(value = "branchId", required = true) String branchId
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                termsService.updateBranchTerm(termId, branchId, request));
    }
}

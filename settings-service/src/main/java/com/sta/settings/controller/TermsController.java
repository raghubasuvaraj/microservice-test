package com.sta.settings.controller;

import com.sta.settings.dto.request.TermListRequest;
import com.sta.settings.dto.request.TermRequest;
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
@RequestMapping("/api/v1/term")
@Slf4j
public class TermsController {
    @Autowired
    private ITermsService termsService;

    @GetMapping("/institute-terms")
    public ResponseEntity<List<InstituteTermEntity>> getAllInstituteTerms(@RequestParam(value = "instituteId", required = true) String instituteId,
                                                                          @RequestParam(value = "termName", required = false) String termName) {
        return ResponseEntity.status(HttpStatus.OK).body(termsService.getAllInstituteTerms(instituteId,termName));
    }

    @GetMapping("/institute-term/{id}")
    public ResponseEntity<InstituteTermEntity> getInstituteTerm(
            @PathVariable("id") String termId,
            @RequestParam(value = "instituteId", required = true) String instituteId) {
        return ResponseEntity.status(HttpStatus.OK).body(termsService.getInstituteTerm(termId,instituteId));
    }

    @PostMapping
    public ResponseEntity<?> createTermsForInstitute(@Valid @RequestBody TermListRequest request,
                                                     @RequestParam(value = "instituteId", required = true) String instituteId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(termsService.saveInstituteTerm(request, instituteId));
    }
    @PutMapping("/institute-term/update")
    public ResponseEntity<InstituteTermEntity> updateInstituteTerm(
            @Valid @RequestBody TermRequest request,
            @RequestParam(value = "termId", required = true) String termId,
            @RequestParam(value = "instituteId", required = true) String instituteId
            ) {
        return ResponseEntity.status(HttpStatus.OK).body(
                termsService.updateInstituteTerm(termId, instituteId, request));
    }
    @DeleteMapping("/institute-term/{id}")
    public ResponseEntity<?> deleteInstituteTerm(@PathVariable String id,
                                              @RequestParam(value = "instituteId", required = true) String instituteId) {
        this.termsService.deleteInstituteTerm(id, instituteId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

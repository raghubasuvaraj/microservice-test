package com.sta.settings.controller;

import com.sta.settings.dto.SubjectDto;
import com.sta.settings.service.ISubjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * A REST handler to cater CRUD operations for subject of a curriculum.
 *
 * @author Shane
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/subject")
public class SubjectController {

    @Autowired
    private ISubjectService subjectService;

    @PostMapping()
    public ResponseEntity<SubjectDto> saveSubject(@Valid @RequestBody SubjectDto subjectDto) {
        log.info("Saving subject for curriculumId - {} ::", subjectDto.getCurriculumId());
        return new ResponseEntity<>(subjectService.saveSubject(subjectDto), HttpStatus.CREATED);
    }

    @PutMapping()
    public ResponseEntity<SubjectDto> updateSubject(@Valid @RequestBody SubjectDto subjectDto) {
        log.info("Updating subject for curriculumId - {} ::", subjectDto.getId());
        return new ResponseEntity<>(subjectService.updateSubject(subjectDto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubjectDto> getSubject(@PathVariable("id") String id) {
        log.info("Getting a subject for given id :: {}", id);
        return new ResponseEntity<>(subjectService.getSubject(id), HttpStatus.OK);
    }

    @GetMapping("/{curriculumId}/all")
    public ResponseEntity<List<SubjectDto>> getAllSubjects(@PathVariable("curriculumId") String curriculumId) {
        log.info("Getting All the subjects of a curriculum.");
        return new ResponseEntity<>(subjectService.getAllSubjects(curriculumId), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBankDetail(@PathVariable("id") String id) {
        log.info("Deleting a Bank Details with id {} ::", id);
        return subjectService.deleteSubjectDto(id);
    }
}

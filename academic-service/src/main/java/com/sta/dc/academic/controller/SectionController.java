package com.sta.dc.academic.controller;

import com.sta.dc.academic.service.ISectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/section")
@Slf4j
public class SectionController {

    @Autowired
    private ISectionService iSectionService;

    /**
     * This API allows to add students to a section.
     *
     * @param sectionId
     * @return Void
     * @apiNote API#02
     * AC-10101
     */
    @PostMapping("/{sectionId}/students/v1")
    public ResponseEntity<Void> addStudentsToSection(@PathVariable String sectionId, @Valid @RequestBody List<String> studentIds) {
        return new ResponseEntity<>(iSectionService.addStudentsToSection(sectionId, studentIds), HttpStatus.CREATED);
    }

}

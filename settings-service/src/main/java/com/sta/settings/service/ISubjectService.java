package com.sta.settings.service;

import com.sta.settings.dto.SubjectDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * A Service interface to cater CRUD operations for subject of a curriculum.
 *
 * @author Shane
 */
public interface ISubjectService {
    SubjectDto saveSubject(SubjectDto subjectDto);
    SubjectDto updateSubject(SubjectDto subjectDto);
    SubjectDto getSubject(String id);
    List<SubjectDto> getAllSubjects(String curriculumId);
    ResponseEntity<String> deleteSubjectDto(String id);
}

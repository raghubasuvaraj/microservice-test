package com.sta.dc.academic.service.impl;

import com.sta.dc.common.dao.academic.SectionDAO;
import com.sta.dc.common.dao.academic.SectionStudentMappingDAO;
import com.sta.dc.common.dao.directory.StudentDetailDAO;
import com.sta.dc.common.entity.academic.AcademicSectionEntity;
import com.sta.dc.common.entity.academic.SectionStudentMappingEntity;
import com.sta.dc.common.entity.directory.StudentDetailEntity;
import com.sta.dc.common.exception.academic.AcademicException;
import com.sta.dc.academic.service.ISectionService;
import com.sta.dc.common.constants.MessageConstants.academic.AcademicMessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SectionServiceImpl implements ISectionService {

    @Autowired
    private StudentDetailDAO studentDetailDAO;

    @Autowired
    private SectionDAO sectionDAO;

    @Autowired
    private SectionStudentMappingDAO sectionStudentMappingDAO;

    // AC-20101
    @Override
    public Void addStudentsToSection(String sectionId, List<String> studentIds) {
        try {
            studentIds.forEach(id -> {
                if (id.equalsIgnoreCase("")) {
                    throw new AcademicException(HttpStatus.BAD_REQUEST.toString(), "Student Ids can not be empty.",
                            HttpStatus.BAD_REQUEST, null);
                }
            });

            List<StudentDetailEntity> studentDetailsByIds =
                    studentDetailDAO.getStudentDetailsByIds(studentIds);

            if (studentDetailsByIds.size() != studentIds.size()) {
                throw new AcademicException(HttpStatus.NOT_FOUND.toString(), AcademicMessageConstants.STUDENT_NOT_FOUND,
                        HttpStatus.NOT_FOUND, null);
            }

            Optional<AcademicSectionEntity> optionalAcademicSection =
                    sectionDAO.getAcademicSectionById(sectionId);

            List<SectionStudentMappingEntity> sectionStudentMappingsToBeSaved = new ArrayList<>();

            AcademicSectionEntity academicSectionEntity;
            if (optionalAcademicSection.isPresent()) {
                if (!studentDetailsByIds.isEmpty()) {
                    academicSectionEntity = optionalAcademicSection.get();
                    studentDetailsByIds.forEach(student -> {
                        SectionStudentMappingEntity sectionStudentMappingEntity = new SectionStudentMappingEntity();
                        sectionStudentMappingEntity.setStudentDetailEntity(student);
                        sectionStudentMappingEntity.setAcademicSectionEntity(academicSectionEntity);
                        sectionStudentMappingEntity.setAcademicClassEntity(academicSectionEntity.getAcademicClass());
                        sectionStudentMappingEntity.setRoll(student.getRollNo());
                        sectionStudentMappingEntity.setAcademicYear(academicSectionEntity.getAcademicClass().getAcademicYear());
                        sectionStudentMappingsToBeSaved.add(sectionStudentMappingEntity);
                    });
                } else {
                    throw new AcademicException(HttpStatus.NOT_FOUND.toString(),
                            AcademicMessageConstants.STUDENT_NOT_FOUND, HttpStatus.NOT_FOUND, null);
                }
            } else {
                throw new AcademicException(HttpStatus.NOT_FOUND.toString(), AcademicMessageConstants.SECTION_NOT_FOUND,
                        HttpStatus.NOT_FOUND, null);
            }

            List<SectionStudentMappingEntity> sectionStudentMappingList =
                    sectionStudentMappingDAO.saveSectionStudentMappings(sectionStudentMappingsToBeSaved);

            academicSectionEntity.
                    setStudentCount(academicSectionEntity.getStudentCount() + sectionStudentMappingList.size());

            sectionDAO.saveAcademicSection(optionalAcademicSection.get());
            return null;
        } catch (AcademicException ae) {
            throw ae;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new AcademicException(HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                    AcademicMessageConstants.FAILED_TO_ADD_STUDENTS, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }
}
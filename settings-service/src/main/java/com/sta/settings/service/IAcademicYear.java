package com.sta.settings.service;


import com.sta.settings.dto.AcademicYearDto;
import com.sta.settings.entities.InstituteAcademicYearEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAcademicYear {
    AcademicYearDto saveAcademicYear(AcademicYearDto academicYearDto);
    AcademicYearDto updateAcademicYear(AcademicYearDto academicYearDto);
    InstituteAcademicYearEntity getAcademicYear(String branchIdOrInstituteId);
    List<InstituteAcademicYearEntity> getAllAcademicYears(String instituteId);
    ResponseEntity<String> deleteAcademicYear(String id);

    InstituteAcademicYearEntity getCurrentAcademicYear();
}

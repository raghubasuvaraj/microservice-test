package com.sta.dc.common.dao.academic;

import com.sta.dc.common.entity.academic.AcademicSectionEntity;

import java.util.Optional;

public interface SectionDAO {

    // AC-30101
    public Optional<AcademicSectionEntity> getAcademicSectionById(String sectionId);

    // AC-30102
    public AcademicSectionEntity saveAcademicSection(AcademicSectionEntity academicSectionEntity);
}

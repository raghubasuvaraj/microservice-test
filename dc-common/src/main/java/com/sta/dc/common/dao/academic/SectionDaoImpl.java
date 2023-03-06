package com.sta.dc.common.dao.academic;

import com.sta.dc.common.entity.academic.AcademicSectionEntity;
import com.sta.dc.common.repository.academic.AcademicSectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SectionDaoImpl implements SectionDAO{

    // AC-30101
    @Autowired
    private AcademicSectionRepository academicSectionRepository;

    // AC-30102
    public Optional<AcademicSectionEntity> getAcademicSectionById(String sectionId) {
        try {
            return academicSectionRepository.findByIdAndActive(sectionId, true);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public AcademicSectionEntity saveAcademicSection(AcademicSectionEntity academicSectionEntity) {
        try {
            return academicSectionRepository.save(academicSectionEntity);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }



}

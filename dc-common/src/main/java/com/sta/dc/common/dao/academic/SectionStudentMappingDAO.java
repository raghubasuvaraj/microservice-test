package com.sta.dc.common.dao.academic;

import com.sta.dc.common.entity.academic.SectionStudentMappingEntity;

import java.util.List;

public interface SectionStudentMappingDAO {

    // AC-30201
    public List<SectionStudentMappingEntity> saveSectionStudentMappings(List<SectionStudentMappingEntity> sectionStudentMappingsToBeSaved);

}

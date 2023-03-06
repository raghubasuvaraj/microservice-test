package com.sta.settings.mappers;

import com.sta.settings.dto.AcademicYearDto;
import com.sta.settings.entities.InstituteAcademicYearEntity;

public interface AcademicYearListMapper {
      static AcademicYearDto apply(InstituteAcademicYearEntity academicYearEntity) {
            return AcademicYearDto.builder()
                    .id(academicYearEntity.getId())
                    .startDate(String.valueOf(academicYearEntity.getStartDate()))
                    .endDate(String.valueOf(academicYearEntity.getEndDate()))
                    .instituteId(academicYearEntity.getInstituteId())
                    .build();
      }
}

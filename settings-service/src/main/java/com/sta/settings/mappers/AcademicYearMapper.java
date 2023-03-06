package com.sta.settings.mappers;

import com.sta.settings.dto.AcademicYearDto;
import com.sta.settings.entities.InstituteAcademicYearEntity;
import com.sta.settings.entities.MasterAcademicYearEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AcademicYearMapper {
    public static InstituteAcademicYearEntity toAcademicYearEntity(AcademicYearDto academicYearDto) {
        return InstituteAcademicYearEntity.builder()
                .startDate(LocalDate.parse(academicYearDto.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE))
                .endDate(LocalDate.parse(academicYearDto.getEndDate(), DateTimeFormatter.ISO_LOCAL_DATE))
                .instituteId(academicYearDto.getInstituteId())
                .build();
    }

    public static MasterAcademicYearEntity toMasterAcademicYearEntity(AcademicYearDto academicYearDto) {
        return MasterAcademicYearEntity.builder()
                .name(academicYearDto.getName())
                .startYear(academicYearDto.getStartDate().split("-")[0])
                .endYear(academicYearDto.getEndDate().split("-")[0])
                .build();
    }

    public static AcademicYearDto toAcademicYearDto(InstituteAcademicYearEntity academicYearEntity) {
        AcademicYearDto academicYearDto = AcademicYearDto.builder()
                .id(academicYearEntity.getId())
                .startDate(String.valueOf(academicYearEntity.getStartDate()))
                .endDate(String.valueOf(academicYearEntity.getEndDate()))
                .instituteId(academicYearEntity.getInstituteId())
                .build();
        academicYearDto.setId(academicYearEntity.getId());
        return academicYearDto;
    }

    public static InstituteAcademicYearEntity toUpdateAcademicYearEntity(AcademicYearDto academicYearDto, InstituteAcademicYearEntity existingAcademicYearEntity) {
        existingAcademicYearEntity.setStartDate(LocalDate.parse(academicYearDto.getStartDate(), DateTimeFormatter.ISO_LOCAL_DATE));
        existingAcademicYearEntity.setEndDate(LocalDate.parse(academicYearDto.getEndDate(),DateTimeFormatter.ISO_LOCAL_DATE));
        existingAcademicYearEntity.setInstituteId(academicYearDto.getInstituteId());
        existingAcademicYearEntity.setId(academicYearDto.getId());
        return existingAcademicYearEntity;
    }


}

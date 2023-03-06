package com.sta.settings.mappers;

import com.sta.settings.dto.SubjectDto;
import com.sta.settings.entities.SubjectEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SubjectMapper {
    public static SubjectEntity requestToEntity(SubjectDto request) {
        return SubjectEntity.builder()
                .curriculumId(request.getCurriculumId())
                .branchId(request.getBranchId())
                .subjectName(request.getSubjectName())
                .shortName(request.getShortName())
                .coScholasticType(request.getCoScholasticType())
                .subjectType(request.getSubjectType())
                .build();
    }

    public static SubjectEntity updateRequestToEntity(SubjectDto request, String id) {
        SubjectEntity subjectEntity = SubjectEntity.builder()
                .curriculumId(request.getCurriculumId())
                .branchId(request.getBranchId())
                .subjectName(request.getSubjectName())
                .shortName(request.getShortName())
                .coScholasticType(request.getCoScholasticType())
                .subjectType(request.getSubjectType())
                .build();
        subjectEntity.setId(id);
        return subjectEntity;
    }

    public static SubjectDto entityToResponse(SubjectEntity subjectEntity) {
        return SubjectDto.builder()
                .id(subjectEntity.getId())
                .curriculumId(subjectEntity.getCurriculumId())
                .branchId(subjectEntity.getBranchId())
                .subjectName(subjectEntity.getSubjectName())
                .shortName(subjectEntity.getShortName())
                .coScholasticType(subjectEntity.getCoScholasticType())
                .subjectType(subjectEntity.getSubjectType())
                .build();
    }

    public static List<SubjectDto> mapAllEntityToResponse(List<SubjectEntity> subjectEntities) {
        return subjectEntities.parallelStream()
                .map(SubjectMapper::entityToResponse)
                .toList();
    }
}


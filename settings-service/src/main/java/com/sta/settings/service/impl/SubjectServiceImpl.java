package com.sta.settings.service.impl;

import com.sta.settings.dto.SubjectDto;
import com.sta.settings.entities.SubjectEntity;
import com.sta.settings.enums.CommonStatus;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.mappers.SubjectMapper;
import com.sta.settings.repository.SubjectRepository;
import com.sta.settings.service.ISubjectService;
import com.sta.dc.common.utility.SettingsConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SubjectServiceImpl implements ISubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public SubjectDto saveSubject(SubjectDto subjectDto) {
        if (subjectRepository.existsByCurriculumIdAndSubjectName(subjectDto.getCurriculumId(), subjectDto.getSubjectName()))
            throw new SettingsException(SettingsConstants.BAD_REQUEST, SettingsConstants.SUBJECT_ALREADY_EXISTS, HttpStatus.BAD_REQUEST, null);
        try {
            SubjectDto response = null;
            SubjectEntity subjectEntity = SubjectMapper.requestToEntity(subjectDto);
            subjectEntity = subjectRepository.save(subjectEntity);
            if (!StringUtils.isBlank(subjectEntity.getId())) {
                log.info("Subject with the Id - {} is created.", subjectEntity.getId());
                response = SubjectMapper.entityToResponse(subjectEntity);
            }
            return response;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.SUBJECT_CREATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public SubjectDto updateSubject(SubjectDto subjectDto) {
        try {
            SubjectDto response = null;
            Optional<SubjectEntity> existingSubjectEntity = subjectRepository.findById(subjectDto.getId());
            if (existingSubjectEntity.isPresent()) {
                SubjectEntity updatedSubjectEntity = SubjectMapper.updateRequestToEntity(subjectDto, existingSubjectEntity.get().getId());
                updatedSubjectEntity = subjectRepository.save(updatedSubjectEntity);
                if (!StringUtils.isBlank(updatedSubjectEntity.getId())) {
                    log.info("Subject updated.");
                    response = SubjectMapper.entityToResponse(updatedSubjectEntity);
                }
            }
            return response;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.SUBJECT_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public SubjectDto getSubject(String id) {
        try {
            SubjectDto response;
            Optional<SubjectEntity> existingSubjectEntity = subjectRepository.findById(id);
            if (existingSubjectEntity.isPresent()) {
                response = SubjectMapper.entityToResponse(existingSubjectEntity.get());
            } else {
                throw new SettingsException(HttpStatus.NOT_FOUND.toString(), SettingsConstants.SUBJECT_NOT_FOUND + id, HttpStatus.NOT_FOUND, null);
            }
            return response;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.SUBJECT_NOT_FOUND + id, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<SubjectDto> getAllSubjects(String curriculumId) {
        try {
            List<SubjectDto> response = new ArrayList<>();
            List<SubjectEntity> existingSubjectEntities = subjectRepository.findAll();
            if (ObjectUtils.isNotEmpty(existingSubjectEntities)) {
                response = SubjectMapper.mapAllEntityToResponse(existingSubjectEntities);
            }
            return response;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.SUBJECT_LIST_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public ResponseEntity<String> deleteSubjectDto(String id) {
        Long currentTime = new Date().getTime();
        if (StringUtils.isBlank(id) || !subjectRepository.existsById(id))
            throw new SettingsException(SettingsConstants.NOT_FOUND, SettingsConstants.SUBJECT_NOT_FOUND, HttpStatus.NOT_FOUND, null);
        try {
            Optional<SubjectEntity> existingSubjectEntityOptional = subjectRepository.findById(id);
            if (existingSubjectEntityOptional.isPresent()) {
                SubjectEntity existingSubjectEntity = existingSubjectEntityOptional.get();
                //existingSubjectEntity.setLastModifiedAt(currentTime);
                existingSubjectEntity.setActive(false);
                existingSubjectEntity.setStatus(CommonStatus.DELETE);
                existingSubjectEntity.setDeletedAt(LocalDateTime.now());
                existingSubjectEntity = subjectRepository.save(existingSubjectEntity);
                if (!existingSubjectEntity.isActive()) {
                    return new ResponseEntity<>(SettingsConstants.SUBJECT_DELETED + id, HttpStatus.ACCEPTED);
                }
            }
            return new ResponseEntity<>(SettingsConstants.SUBJECT_NOT_DELETED + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.SUBJECT_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }
}


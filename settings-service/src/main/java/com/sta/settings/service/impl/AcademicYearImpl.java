package com.sta.settings.service.impl;

import com.sta.settings.dto.AcademicYearDto;
import com.sta.settings.entities.InstituteAcademicYearEntity;
import com.sta.settings.entities.MasterAcademicYearEntity;
import com.sta.settings.enums.CommonStatus;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.mappers.AcademicYearMapper;
import com.sta.settings.repository.AcademicYearRepository;
import com.sta.settings.repository.MasterAcademicYearRepository;
import com.sta.settings.service.IAcademicYear;
import com.sta.settings.utility.DateUtils;
import com.sta.dc.common.utility.SettingsConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class AcademicYearImpl implements IAcademicYear {

    @Autowired
    private AcademicYearRepository academicYearRepository;

    @Autowired
    private MasterAcademicYearRepository masterAcademicYearRepository;

    @Override
    public AcademicYearDto saveAcademicYear(AcademicYearDto academicYearDto) {
        AcademicYearDto response = null;
        MasterAcademicYearEntity masterAcademicYearEntity;
        try {
            masterAcademicYearEntity = masterAcademicYearRepository.findByName(academicYearDto.getName());
            if (masterAcademicYearEntity == null || masterAcademicYearEntity.getId() == null) {
                masterAcademicYearEntity = AcademicYearMapper.toMasterAcademicYearEntity(academicYearDto);
                masterAcademicYearEntity = masterAcademicYearRepository.save(masterAcademicYearEntity);
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.ACADEMIC_YEAR_CREATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
        if (academicYearRepository.existsByYearIdAndInstituteId(masterAcademicYearEntity.getId(), academicYearDto.getInstituteId()))
            throw new SettingsException(SettingsConstants.BAD_REQUEST, SettingsConstants.ACADEMIC_YEAR_ALREADY_EXISTS, HttpStatus.BAD_REQUEST, null);
        try {
            InstituteAcademicYearEntity academicYearEntity = AcademicYearMapper.toAcademicYearEntity(academicYearDto);
            academicYearEntity.setYearId(masterAcademicYearEntity.getId());
            academicYearEntity.setActive(true);
            academicYearEntity = academicYearRepository.save(academicYearEntity);
            if (!StringUtils.isBlank(academicYearEntity.getId())) {
                log.info("Academic year created.");
                response = AcademicYearMapper.toAcademicYearDto(academicYearEntity);
                response.setName(masterAcademicYearEntity.getName());
            }
            return response;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.ACADEMIC_YEAR_CREATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public AcademicYearDto updateAcademicYear(AcademicYearDto academicYearDto) {
        try {
            AcademicYearDto response = null;
            Optional<InstituteAcademicYearEntity> existingAcademicYearEntity = academicYearRepository.findById(academicYearDto.getId());
            if (existingAcademicYearEntity.isPresent()) {
                InstituteAcademicYearEntity updatedAcademicEntity = AcademicYearMapper.toUpdateAcademicYearEntity(academicYearDto, existingAcademicYearEntity.get());
                updatedAcademicEntity = academicYearRepository.save(updatedAcademicEntity);
                if (!StringUtils.isBlank(updatedAcademicEntity.getId())) {
                    log.info("Academic year updated.");
                    response = AcademicYearMapper.toAcademicYearDto(updatedAcademicEntity);
                    response.setName(academicYearDto.getName());
                }
            }
            return response;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.ACADEMIC_YEAR_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public InstituteAcademicYearEntity getAcademicYear(String id) {
        try {
            InstituteAcademicYearEntity academicYearEntity = academicYearRepository.findByIdWithName(id);
            if (ObjectUtils.isNotEmpty(academicYearEntity)) {
                return academicYearEntity;
            } else {
                throw new SettingsException(HttpStatus.NOT_FOUND.toString(), SettingsConstants.ACADEMIC_YEAR_NOT_FOUND + id, HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.NOT_FOUND, SettingsConstants.ACADEMIC_YEAR_NOT_FOUND + id, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<InstituteAcademicYearEntity> getAllAcademicYears(String instituteId) {
        try {
            List<InstituteAcademicYearEntity> response = new ArrayList<>();
            List<InstituteAcademicYearEntity> academicYearEntities = academicYearRepository.findByInstituteIdWithName(instituteId);
            if (ObjectUtils.isNotEmpty(academicYearEntities)) {
                return academicYearEntities;
            }
            return response;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.ACADEMIC_YEAR_LIST_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public ResponseEntity<String> deleteAcademicYear(String id) {
        if (StringUtils.isBlank(id) || !academicYearRepository.existsById(id))
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.ACADEMIC_YEAR_NOT_FOUND, HttpStatus.INTERNAL_SERVER_ERROR, null);
        boolean response = false;
        try {
            Optional<InstituteAcademicYearEntity> academicYearEntity = academicYearRepository.findById(id);
            if (academicYearEntity.isPresent()) {
                InstituteAcademicYearEntity needToDeleteAcademicEntity = academicYearEntity.get();
                needToDeleteAcademicEntity.setDeletedAt(LocalDateTime.now());
                needToDeleteAcademicEntity.setActive(false);
                needToDeleteAcademicEntity.setStatus(CommonStatus.DELETE);
                needToDeleteAcademicEntity = academicYearRepository.save(needToDeleteAcademicEntity);
                response = !needToDeleteAcademicEntity.isActive();
            }
            if (response) {
                return new ResponseEntity<>(SettingsConstants.ACADEMIC_YEAR_DELETED + id, HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(SettingsConstants.ACADEMIC_YEAR_NOT_DELETED + id, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.ACADEMIC_YEAR_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public InstituteAcademicYearEntity getCurrentAcademicYear() {
        try {
            String currentTime = new SimpleDateFormat(SettingsConstants.DATE_FORMAT_YYYYMMDD).format(Calendar.getInstance().getTime());
            LocalDate d1 = DateUtils.convertStringToJustLocalDate(currentTime, SettingsConstants.DATE_FORMAT_YYYYMMDD);
            List<Predicate> predicates = new ArrayList<>();
            Specification<InstituteAcademicYearEntity> spec = (root, query, criteriaBuilder) -> {
                predicates.add(criteriaBuilder.between(criteriaBuilder.literal(d1),
                        root.get("startDate"),
                        root.get("endDate")));
                return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
            };
            Optional<InstituteAcademicYearEntity> optAcademicYearObj = academicYearRepository.findOne(spec);
            if (optAcademicYearObj.isPresent())
                return optAcademicYearObj.get();
            else {
                return null;
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.GET_CURRENT_ACADEMIC_YEAR_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }
}

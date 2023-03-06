package com.sta.settings.service.impl;

import com.sta.settings.dto.InstitutesSystemInfoDto;
import com.sta.settings.entities.InstituteSystemInfoEntity;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.mappers.InstituteSystemInfoMapperUtil;
import com.sta.settings.mappers.InstitutesSystemInfoListMapper;
import com.sta.settings.repository.InstitutesSystemInfoRepository;
import com.sta.settings.service.IInstitutesSystemInfoService;
import com.sta.dc.common.utility.SettingsConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class InstitutesSystemInfoServiceImpl implements IInstitutesSystemInfoService {

    @Autowired
    private InstitutesSystemInfoRepository institutesSystemInfoRepository;


    @Override
    public InstitutesSystemInfoDto saveInstituteSystemInfo(InstitutesSystemInfoDto institutesSystemInfoDto) {

        if (institutesSystemInfoRepository.existsByInstituteId(institutesSystemInfoDto.getInstituteId()))
            throw new SettingsException(SettingsConstants.BAD_REQUEST, SettingsConstants.INSTITUTE_INFO_ALREADY_EXISTS, HttpStatus.BAD_REQUEST, null);

        try {
            InstitutesSystemInfoDto response = null;
            InstituteSystemInfoEntity toInstituteSystemInfoEntity = InstituteSystemInfoMapperUtil.toInstituteSystemInfoEntity(institutesSystemInfoDto);
            toInstituteSystemInfoEntity.setActive(true);
            toInstituteSystemInfoEntity = institutesSystemInfoRepository.save(toInstituteSystemInfoEntity);
            if (!StringUtils.isBlank(toInstituteSystemInfoEntity.getId())) {
                log.info("Institute created.");
                response = InstituteSystemInfoMapperUtil.toInstituteSystemInfoDto(toInstituteSystemInfoEntity);
            }
            return response;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.INSTITUTE_INFO_CREATE_FAILED,HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }
    @Override
    public InstitutesSystemInfoDto updateInstituteSystemInfo(InstitutesSystemInfoDto institutesSystemInfoDto, HttpServletRequest request) {
            try {
                InstitutesSystemInfoDto response = null;
                InstituteSystemInfoEntity existingInstituteSystemInfoEntity = institutesSystemInfoRepository.findByInstituteId(institutesSystemInfoDto.getInstituteId());
                if (ObjectUtils.isNotEmpty(existingInstituteSystemInfoEntity)){
                    InstituteSystemInfoEntity updatedInstituteSystemInfoEntity = InstituteSystemInfoMapperUtil.toUpdateInstituteSystemInfoEntity(institutesSystemInfoDto, existingInstituteSystemInfoEntity.getId());
                    updatedInstituteSystemInfoEntity.setActive(true);
                    existingInstituteSystemInfoEntity.setLastModifiedAt(LocalDateTime.now());
                    updatedInstituteSystemInfoEntity = institutesSystemInfoRepository.save(updatedInstituteSystemInfoEntity);
                    if (!StringUtils.isBlank(updatedInstituteSystemInfoEntity.getId())) {
                        log.info("Institute updated.");
                        response = InstituteSystemInfoMapperUtil.toInstituteSystemInfoDto(updatedInstituteSystemInfoEntity);
                    }
                }
                return response;
            } catch (Exception e) {
                log.error(ExceptionUtils.getStackTrace(e));
                throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.INSTITUTE_INFO_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
            }
        }
    @Override
    public List<InstitutesSystemInfoDto> getAllInstituteSystemInfo() {
        try {
            List<InstitutesSystemInfoDto> response = null;
            List<InstituteSystemInfoEntity> existingInstituteSystemInfoEntities = institutesSystemInfoRepository.findAll();
            if (ObjectUtils.isNotEmpty(existingInstituteSystemInfoEntities)){
                response = existingInstituteSystemInfoEntities.parallelStream().map(InstitutesSystemInfoListMapper::apply).toList();
            }
            return response;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.INSTITUTE_INFO_LIST_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }
    @Override
    public InstitutesSystemInfoDto getInstituteSystemInfoByInstituteId(String instituteId) {
        try {
            InstitutesSystemInfoDto response;
            InstituteSystemInfoEntity existingInstituteSystemInfoEntity = institutesSystemInfoRepository.findByInstituteId(instituteId);
            if (ObjectUtils.isNotEmpty(existingInstituteSystemInfoEntity)){
                response = InstituteSystemInfoMapperUtil.toInstituteSystemInfoDto(existingInstituteSystemInfoEntity);
            } else {
                throw new SettingsException(HttpStatus.NOT_FOUND.toString(), SettingsConstants.INSTITUTE_INFO_NOT_FOUND + instituteId, HttpStatus.NOT_FOUND, null);
            }
            return response;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.INSTITUTE_INFO_NOT_FOUND + instituteId, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }
    @Override
    public ResponseEntity<String> deleteInstituteSystemInfoByInstituteId(String instituteId) {
        InstituteSystemInfoEntity existingInstituteSystemInfoEntity = institutesSystemInfoRepository.findByInstituteId(instituteId);
        if (StringUtils.isBlank(instituteId) || existingInstituteSystemInfoEntity == null || existingInstituteSystemInfoEntity.getId() == null)
            throw new SettingsException(SettingsConstants.NOT_FOUND, SettingsConstants.INSTITUTE_INFO_NOT_FOUND, HttpStatus.NOT_FOUND, null);
        try {
            existingInstituteSystemInfoEntity.setDeletedAt(LocalDateTime.now());
            existingInstituteSystemInfoEntity.setActive(false);
            existingInstituteSystemInfoEntity = institutesSystemInfoRepository.save(existingInstituteSystemInfoEntity);
            if (!existingInstituteSystemInfoEntity.isActive()){
                return new ResponseEntity<>(SettingsConstants.INSTITUTE_INFO_DELETED + instituteId, HttpStatus.ACCEPTED);
            }
            return new ResponseEntity<>(SettingsConstants.INSTITUTE_INFO_NOT_DELETED + instituteId, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.INSTITUTE_INFO_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

}

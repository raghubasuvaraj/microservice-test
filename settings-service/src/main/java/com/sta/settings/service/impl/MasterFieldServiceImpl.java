package com.sta.settings.service.impl;

import com.sta.settings.dto.request.MasterFieldRequest;
import com.sta.settings.dto.response.MasterFieldResponse;
import com.sta.settings.entities.MasterFieldEntity;
import com.sta.settings.enums.CommonStatus;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.mappers.MasterFieldMapper;
import com.sta.settings.repository.MasterFieldRepository;
import com.sta.settings.service.IMasterFieldService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.bridge.ICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MasterFieldServiceImpl implements IMasterFieldService {
    @Autowired
    private MasterFieldRepository masterFieldRepository;
    @Override
    public MasterFieldResponse saveOrUpdateMasterField(List<MasterFieldRequest> masterFieldRequestList) {
        MasterFieldEntity et = null;
        try{
            for (MasterFieldRequest masterFieldRequest : masterFieldRequestList) {
                if(StringUtils.isNotEmpty(masterFieldRequest.getId())){
                    Optional<MasterFieldEntity> dbMasterFieldEntity = masterFieldRepository.findById(masterFieldRequest.getId());
                    if (dbMasterFieldEntity.isPresent()) {
                        MasterFieldEntity masterFieldEntity = MasterFieldMapper.toMasterFieldEntity(masterFieldRequest);
                        masterFieldEntity.setId(dbMasterFieldEntity.get().getId());
                         et =masterFieldRepository.save(masterFieldEntity);
                    }
                }else{
                    MasterFieldEntity masterFieldEntity = MasterFieldMapper.toMasterFieldEntity(masterFieldRequest);
                    et = masterFieldRepository.save(masterFieldEntity);
                }
            }
        }catch (Exception exception){
            log.error("Error while saveOrUpdate MasterField.....{}",exception.getStackTrace());
            throw new SettingsException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Failed to update master fields system information", HttpStatus.INTERNAL_SERVER_ERROR, exception);
        }

        return MasterFieldMapper.convertEntityToDto(et);
    }
    @Override
    public MasterFieldResponse getMasterField(String fieldId) {
        MasterFieldResponse masterFieldResponse = null;
        try {
            Optional<MasterFieldEntity> masterFieldEntity= masterFieldRepository.findById(fieldId);
            if (masterFieldEntity.isPresent()){
                masterFieldResponse= MasterFieldMapper.convertEntityToDto(masterFieldEntity.get());
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Failed to find master field by field Id::"+fieldId, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
        return masterFieldResponse;
    }
    public List<MasterFieldResponse> getAllMasterField() {
        List<MasterFieldResponse> response;
        try {
            response = new ArrayList<>();
            List<MasterFieldEntity> masterFieldEntities = masterFieldRepository.findAll();
            if (ObjectUtils.isNotEmpty(masterFieldEntities)) {
                response = masterFieldEntities.stream().map(MasterFieldMapper::convertEntityToDto).toList();
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Failed to find master fields", HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
        return response;
    }
    @Override
    public MasterFieldResponse deleteMasterField(String fieldId) {
        Long currentTime = new Date().getTime();
        if (StringUtils.isBlank(fieldId) || !masterFieldRepository.existsById(fieldId))
            throw new SettingsException(HttpStatus.NOT_FOUND.toString(), "Master field system info does not exist.",HttpStatus.NOT_FOUND, null);

        try {
            Optional<MasterFieldEntity> dbMasterFieldEntity = masterFieldRepository.findById(fieldId);
            if(dbMasterFieldEntity.isPresent()) {
                MasterFieldEntity masterFieldEntity = dbMasterFieldEntity.get();
                //masterFieldEntity.setLastModifiedAt(currentTime);
                masterFieldEntity.setActive(false);
                masterFieldEntity.setStatus(CommonStatus.DELETE);
                masterFieldEntity.setDeletedAt(LocalDateTime.now());
                masterFieldEntity = masterFieldRepository.save(masterFieldEntity);
                MasterFieldMapper.convertEntityToDto(masterFieldEntity);
                return MasterFieldMapper.convertEntityToDto(masterFieldEntity);
            } else {
                throw new SettingsException(HttpStatus.NOT_FOUND.toString(), "Master field system info does not exist.",HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Failed to delete master field.", HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }
}

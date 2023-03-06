package com.sta.settings.service.impl;

import com.sta.settings.dto.request.BranchAreaRequest;
import com.sta.settings.dto.response.BranchAreaResponse;
import com.sta.settings.entities.BranchAreaEntity;
import com.sta.settings.enums.CommonStatus;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.mappers.BranchAreaMapper;
import com.sta.settings.repository.BranchAreaRepository;
import com.sta.settings.service.IBranchAreaService;
import com.sta.settings.utility.ErrorCodesConstants;
import com.sta.settings.utility.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BranchAreaServiceImpl implements IBranchAreaService {

    @Autowired
    BranchAreaRepository branchAreaRepository;

    @Override
    public List<BranchAreaResponse> saveOrUpdateBranchArea(List<BranchAreaRequest> branchAreaRequestsList) {
        BranchAreaEntity branchAreaEntity = null;
        List<BranchAreaResponse> branchAreaResponseList = new ArrayList<>();
        try{
            for (BranchAreaRequest branchAreaRequest : branchAreaRequestsList) {
                if(StringUtils.isNotEmpty(branchAreaRequest.getId())){
                    Optional<BranchAreaEntity> entity = branchAreaRepository.findById(branchAreaRequest.getId());
                    if (entity.isPresent()) {
                        BranchAreaEntity branchArea = BranchAreaMapper.convertBranchAreaRequestToEntity(branchAreaRequest);
                        branchArea.setId(entity.get().getId());
                        branchAreaEntity =branchAreaRepository.save(branchArea);
                        branchAreaResponseList.add(BranchAreaMapper.convertBranchAreaEntityToResponse(branchAreaEntity));
                    }
                }else{
                    BranchAreaEntity entity = BranchAreaMapper.convertBranchAreaRequestToEntity(branchAreaRequest);
                    branchAreaEntity = branchAreaRepository.save(entity);
                    branchAreaResponseList.add(BranchAreaMapper.convertBranchAreaEntityToResponse(branchAreaEntity));
                }
            }
        }catch (Exception exception){
            log.error("Error while saveOrUpdate BranchArea.....{}",exception.getStackTrace());
            throw new SettingsException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Failed to Create/Update BranchArea", HttpStatus.INTERNAL_SERVER_ERROR, exception);
        }

        return branchAreaResponseList;
    }

    public void deleteBranchArea(String fieldId) {
        Long currentTime = new Date().getTime();
        if (StringUtils.isBlank(fieldId) || !branchAreaRepository.existsById(fieldId))
            throw new SettingsException(HttpStatus.NOT_FOUND.toString(), "BranchArea info does not exist.",HttpStatus.NOT_FOUND, null);

        try {
            Optional<BranchAreaEntity> branchAreaEntity = branchAreaRepository.findById(fieldId);
            if(branchAreaEntity.isPresent()) {
                BranchAreaEntity entity = branchAreaEntity.get();
                entity.setActive(false);
                entity.setStatus(CommonStatus.DELETE);
                entity.setDeletedAt(LocalDateTime.now());
                entity.setStatus(branchAreaEntity.get().getStatus());
                entity = branchAreaRepository.save(entity);
            } else {
                throw new SettingsException(HttpStatus.NOT_FOUND.toString(), "BranchArea  does not exist.",HttpStatus.NOT_FOUND, null);
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Failed to delete BranchArea.", HttpStatus.INTERNAL_SERVER_ERROR,e);
        }
    }

    public List<BranchAreaResponse> getAllBranchAreas(String branchId) {
        List<BranchAreaResponse> response;
        try {
            response = new ArrayList<>();
            List<BranchAreaEntity> branchAreaEntityList = branchAreaRepository.findByBranchIdIs(branchId);
            if (ObjectUtils.isNotEmpty(branchAreaEntityList)) {
                response = branchAreaEntityList.stream().map(BranchAreaMapper::convertBranchAreaEntityToResponse).toList();
            }
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Failed to find BranchArea", HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
        return response;
    }

    public BranchAreaResponse getBranchAreasById(String id) {

        try {
            BranchAreaResponse response = null;
            Optional<BranchAreaEntity> branchAreaEntity = branchAreaRepository.findById(id);
            if (branchAreaEntity.isPresent()) {
                return  BranchAreaMapper.convertBranchAreaEntityToResponse(branchAreaEntity.get());
            } else {
                throw new SettingsException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Failed to find BranchArea", HttpStatus.INTERNAL_SERVER_ERROR, null);
            }
        } catch (SettingsException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.AREA_NOT_FOUND,
                    HttpStatus.BAD_GATEWAY, null);
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }
}

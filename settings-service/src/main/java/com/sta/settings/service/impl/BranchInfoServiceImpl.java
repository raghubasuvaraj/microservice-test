package com.sta.settings.service.impl;

import com.google.common.base.Enums;
import com.sta.settings.dto.request.BranchApproveRequest;
import com.sta.settings.dto.request.BranchRequest;

import com.sta.settings.dto.request.RequestForMainBranch;
import com.sta.settings.dto.response.BranchResponse;
import com.sta.settings.entities.BranchInfoEntity;
import com.sta.settings.enums.ApprovedStatus;
import com.sta.settings.enums.CommonStatus;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.mappers.BranchInfoMapper;
import com.sta.settings.repository.InstitutesRepository;
import com.sta.settings.service.IBranchInfoService;
import com.sta.settings.repository.BranchInfoRepository;
import com.sta.settings.utility.ErrorCodesConstants;
import com.sta.settings.utility.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BranchInfoServiceImpl implements IBranchInfoService {

    @Autowired
    BranchInfoRepository branchInfoRepository;
    @Autowired
    private InstitutesRepository institutesRepository;

    @Override
    public BranchResponse getBranchById(String branchId) {
        BranchInfoEntity branchInfo = branchInfoRepository.getReferenceById(branchId);
        return BranchInfoMapper.convertBranchEntityTOResponseDTO(branchInfo);
    }

    @Override
    public BranchResponse saveBranch(BranchRequest branchDto) {
        BranchResponse branchResponse;
            if(branchInfoRepository.existsByInstituteIdAndBranchName(branchDto.getInstituteId(), branchDto.getBranchName()))
                throw new SettingsException(HttpStatus.NOT_FOUND.toString(),"Branch Already Exist with BranchName  "+branchDto.getBranchName(), HttpStatus.NOT_FOUND,null);

            BranchInfoEntity branchEntity = BranchInfoMapper.convertBranchDTOtoEntity(branchDto);
            BranchInfoEntity branchInfoEntity= branchInfoRepository.save(branchEntity);
            log.info("Branch Created with BranchID ::");
            branchResponse = BranchInfoMapper.convertBranchEntityTOResponseDTO(branchInfoEntity);

        return branchResponse;
    }
    public List<BranchResponse> getAllBranches(String instituteID){
        List<BranchResponse> responseList =  new ArrayList<>();
        try{
            List<BranchInfoEntity> entityList =branchInfoRepository.findByInstituteIdIs(instituteID);
            for (BranchInfoEntity branchEntity: entityList ) {
                responseList.add(BranchInfoMapper.convertBranchEntityTOResponseDTO(branchEntity));
            }
        }catch (Exception e){
            throw new SettingsException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error while retrieving banch Info.", HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
        return  responseList;
    }

    public BranchResponse updateBranchDetails(BranchRequest branchRequest){
        BranchInfoEntity branchInfoEntity= branchInfoRepository.findByIdAndInstituteId(
                branchRequest.getBranchId(), branchRequest.getInstituteId());
            if(!StringUtils.isBlank(branchInfoEntity.getId())){
                BranchInfoEntity entity = BranchInfoMapper.convertBranchDTOtoEntity(branchRequest);
                entity.setId(branchInfoEntity.getId());
                branchInfoEntity =  branchInfoRepository.save(entity);
            }

        return BranchInfoMapper.convertBranchEntityTOResponseDTO(branchInfoEntity);
    }

    public boolean deleteBranch(String branchId){
        Optional<BranchInfoEntity> branchInfoEntity = branchInfoRepository.findById(branchId);
        if(branchInfoEntity.isPresent()) {
            BranchInfoEntity branchInfo = branchInfoEntity.get();
            branchInfo.setActive(false);
            branchInfo = branchInfoRepository.save(branchInfo);
            return !branchInfo.isActive();
        }
        return false;
    }
    @Override
    public BranchInfoEntity updateBranchToMainBranch(String instituteId, String branchId, RequestForMainBranch request) {
        try {
            institutesRepository.findByIdAndActive(instituteId, true)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.INSTITUTE_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            BranchInfoEntity branchInfoEntity = branchInfoRepository.findById(branchId)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.BRANCH_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            List<BranchInfoEntity> entityList = branchInfoRepository.findByInstituteIdIs(instituteId);
            if (entityList != null) {
                entityList.stream().filter(branchObj -> branchObj.getInstituteId().equalsIgnoreCase(instituteId))
                        .map(branchObj -> {
                            branchObj.setIsMainBranch(false);
                            return branchObj;
                        }).collect(Collectors.toList());
                branchInfoEntity.setIsMainBranch(request.getIsMainBranch());
            }
            return branchInfoRepository.save(branchInfoEntity);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.MAIN_BRANCH_UPDATED_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public BranchResponse approveOrRejectBranch(BranchApproveRequest request) {
        log.info("Branch status changing into " + request.getApprovedStatus() + "...");

        if (!Enums.getIfPresent(ApprovedStatus.class, request.getApprovedStatus()).isPresent())
            throw new SettingsException(ErrorCodesConstants.BAD_REQUEST, MessageConstants.INVALID_STATUS,
                    HttpStatus.BAD_REQUEST, null);

        if (!branchInfoRepository.existsById(request.getId()))
            throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.BRANCH_NOT_FOUND,
                    HttpStatus.NOT_FOUND, null);

        try {
            BranchResponse response = null;
            BranchInfoEntity branchEntity = branchInfoRepository.getReferenceById(request.getId());
            String oldStatus = branchEntity.getStatus().getValue();

            branchEntity.setStatus(CommonStatus.valueOf(request.getApprovedStatus()));
            if (ApprovedStatus.valueOf(request.getApprovedStatus()) == ApprovedStatus.APPROVED) {
                log.info("Branch approved and status turning to active");
                branchEntity.setActive(true);
            }
            if (!StringUtils.isBlank(request.getComments()))
                branchEntity.setComments(request.getComments());

            branchEntity = branchInfoRepository.save(branchEntity);
            if (!oldStatus.equals(branchEntity.getStatus().getCode())) {
                log.info("Branch status changed to " + request.getApprovedStatus());
                response = BranchInfoMapper.convertBranchEntityTOResponseDTO(branchEntity);
            }
            return response;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR,
                    MessageConstants.BRANCH_STATUS_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }    }
}

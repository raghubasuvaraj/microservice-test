package com.sta.settings.service.impl;

import com.sta.settings.dto.BankDetailDto;
import com.sta.settings.entities.BankDetailEntity;
import com.sta.settings.enums.CommonStatus;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.mappers.BankDetailMapper;
import com.sta.settings.repository.BankDetailRepository;
import com.sta.settings.service.IBankDetailService;
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

/**
 * A Service class to cater CRUD operations for Bank detail of a branch.
 *
 * @author Shane
 */
@Slf4j
@Service
public class BankDetailServiceImpl implements IBankDetailService {

    @Autowired
    private BankDetailRepository bankDetailRepository;

    @Override
    public BankDetailDto saveBankDetail(BankDetailDto bankDetailDto) {
        if (bankDetailRepository.existsByBranchIdAndBankAccountNumber(bankDetailDto.getBranchId(), bankDetailDto.getBankAccountNumber()))
            throw new SettingsException(SettingsConstants.BAD_REQUEST, SettingsConstants.BANK_DETAIL_ALREADY_EXISTS, HttpStatus.BAD_REQUEST, null);
        try {
            BankDetailDto response = null;
            BankDetailEntity bankDetailEntity = BankDetailMapper.requestToEntity(bankDetailDto);
            bankDetailEntity = bankDetailRepository.save(bankDetailEntity);
            if (!StringUtils.isBlank(bankDetailEntity.getId())) {
                log.info("Bank Detail for the branch ID - {} is created.", bankDetailDto.getBranchId());
                response = BankDetailMapper.entityToResponse(bankDetailEntity);
            }
            return response;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.BANK_DETAIL_CREATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public BankDetailDto updateBankDetail(BankDetailDto bankDetailDto) {
        try {
            BankDetailDto response = null;
            Optional<BankDetailEntity> existingBankDetailEntity = bankDetailRepository.findById(bankDetailDto.getId());
            if (existingBankDetailEntity.isPresent()){
                BankDetailEntity updatedBankDetailEntity = BankDetailMapper.updateRequestToEntity(bankDetailDto, existingBankDetailEntity.get().getId());
                updatedBankDetailEntity.setCreatedAt(LocalDateTime.now());
                //updatedBankDetailEntity.setCreatedBy("System");
                updatedBankDetailEntity = bankDetailRepository.save(updatedBankDetailEntity);
                if (!StringUtils.isBlank(updatedBankDetailEntity.getId())) {
                    log.info("Bank detail updated.");
                    response = BankDetailMapper.entityToResponse(updatedBankDetailEntity);
                }
            }
            return response;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.BANK_DETAIL_UPDATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<BankDetailDto> getBankDetail(String branchIdOrBankDetailId) {
        try {
            List<BankDetailDto> response;
            List<BankDetailEntity> existingBankDetailEntities = bankDetailRepository.findByBranchIdOrBankDetailId(branchIdOrBankDetailId);
            if (ObjectUtils.isNotEmpty(existingBankDetailEntities)){
                response = BankDetailMapper.mapAllEntityToResponse(existingBankDetailEntities);
            } else {
                throw new SettingsException(HttpStatus.NOT_FOUND.toString(), SettingsConstants.BANK_DETAIL_NOT_FOUND + branchIdOrBankDetailId, HttpStatus.NOT_FOUND, null);
            }
            return response;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.BANK_DETAIL_NOT_FOUND + branchIdOrBankDetailId, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<BankDetailDto> getAllBankDetails() {
        try {
            List<BankDetailDto> response = new ArrayList<>();
            List<BankDetailEntity> existingBankDetailEntities = bankDetailRepository.findAll();
            if (ObjectUtils.isNotEmpty(existingBankDetailEntities)) {
                response = BankDetailMapper.mapAllEntityToResponse(existingBankDetailEntities);
            }
            return response;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.BANK_DETAIL_LIST_ALL_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public ResponseEntity<String> deleteBankDetail(String bankId) {
        Long currentTime = new Date().getTime();
        if (StringUtils.isBlank(bankId) || !bankDetailRepository.existsById(bankId))
            throw new SettingsException(SettingsConstants.NOT_FOUND, SettingsConstants.BANK_DETAIL_NOT_FOUND, HttpStatus.NOT_FOUND, null);
        try {
            Optional<BankDetailEntity> existingBankDetailEntityOptional = bankDetailRepository.findById(bankId);
            if (existingBankDetailEntityOptional.isPresent()) {
                BankDetailEntity existingBankDetailEntity = existingBankDetailEntityOptional.get();
                existingBankDetailEntity.setLastModifiedAt(LocalDateTime.now());
               // existingBankDetailEntity.setModifiedBy("System");
                existingBankDetailEntity.setActive(false);
                existingBankDetailEntity.setStatus(CommonStatus.DELETE);
                existingBankDetailEntity.setDeletedAt(LocalDateTime.now());
                existingBankDetailEntity = bankDetailRepository.save(existingBankDetailEntity);
                if (!existingBankDetailEntity.isActive()){
                    return new ResponseEntity<>(SettingsConstants.BANK_DETAIL_DELETED + bankId, HttpStatus.ACCEPTED);
                }
            }
            return new ResponseEntity<>(SettingsConstants.BANK_DETAIL_NOT_DELETED + bankId, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(SettingsConstants.INTERNAL_SERVER_ERROR, SettingsConstants.BANK_DETAIL_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }
}

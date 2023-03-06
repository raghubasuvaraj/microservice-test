package com.sta.settings.service.impl;

import com.sta.settings.dto.ModuleMapperDto;
import com.sta.settings.entities.BranchModuleEntity;
import com.sta.settings.enums.ApprovedStatus;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.mappers.ModuleMapper;
import com.sta.settings.repository.BranchModuleRepository;
import com.sta.settings.service.IModuleMapperService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
public class ModuleMapperServiceImpl implements IModuleMapperService {

    @Autowired
    private BranchModuleRepository branchModuleRepository;

    private ModuleMapper moduleMapper;

    @Override
    public ModuleMapperDto updateModule(ModuleMapperDto moduleMapperDto, HttpServletRequest request) {
        try {
            List<String> existingMappedModuleList = branchModuleRepository
                    .findByBranchIdOrModuleIdIn(moduleMapperDto.getBranchId(), moduleMapperDto.getModulesList());

            List<String> toBeMappedModuleList = new ArrayList<>(moduleMapperDto.getModulesList());
            toBeMappedModuleList.removeAll(existingMappedModuleList);

            if (ObjectUtils.isEmpty(toBeMappedModuleList)) {
                log.info("All modules are already mapped for branchId :: {}", moduleMapperDto.getBranchId());
                moduleMapperDto.setModulesList(Collections.emptyList());
            } else {
                List<BranchModuleEntity> branchModuleEntities = new ArrayList<>();
                toBeMappedModuleList.forEach(obj -> {
                    BranchModuleEntity branchModuleEntity = new BranchModuleEntity();
                    branchModuleEntity.setModuleId(obj);
                    branchModuleEntity.setBranchId(moduleMapperDto.getBranchId());
                    branchModuleEntity.setStatus(ApprovedStatus.PENDING.getCode());
                    branchModuleEntities.add(branchModuleEntity);
                });
                branchModuleRepository.saveAll(branchModuleEntities);
                moduleMapperDto.setModulesList(toBeMappedModuleList);
            }
            return moduleMapperDto;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Failed to update module", HttpStatus.INTERNAL_SERVER_ERROR,e);

        }
    }


    @Override
    public List<BranchModuleEntity> getAllModule() {
        try {
            return branchModuleRepository.findAllActive();
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Failed to list all modules", HttpStatus.INTERNAL_SERVER_ERROR,e);

        }
    }

    @Override
    public List<BranchModuleEntity> getModuleByBranchId(String branchId) {
        try {
            return branchModuleRepository.findByBranchId(branchId);
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Failed to find module by branchId::" + branchId, HttpStatus.INTERNAL_SERVER_ERROR,e);

        }
    }

}

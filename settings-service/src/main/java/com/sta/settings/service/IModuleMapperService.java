package com.sta.settings.service;

import com.sta.settings.dto.ModuleMapperDto;
import com.sta.settings.entities.BranchModuleEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IModuleMapperService {
    ModuleMapperDto updateModule(ModuleMapperDto moduleRequestDto, HttpServletRequest request);
    List<BranchModuleEntity> getAllModule();
    List<BranchModuleEntity> getModuleByBranchId(String branchId);
}

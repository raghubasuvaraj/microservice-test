package com.sta.settings.service;

import com.sta.dc.common.entity.academic.MasterAcademicClassesEntity;
import com.sta.settings.dto.request.CurriculumRequest;
import com.sta.settings.dto.request.CurriculumRequestList;
import com.sta.settings.entities.*;
import com.sta.settings.exception.SettingsException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ISettingsService {
    List<MasterFieldsDataEntity> getAllMasterFields(String categoryName,String name);

    List<LanguageEntity> getAllLanguages(String name);

    List<MasterBranchTypeEntity> getAllBranchTypes(String branchTypeName);

    List<ClassGroupsEntity> getAllClassGroups();

    List<InstituteCurriculumEntity> createCurriculums(CurriculumRequestList request, String instituteId) throws SettingsException;

    List<InstituteCurriculumEntity> getAllCurriculums(String instituteId, String curriculumName);

    InstituteCurriculumEntity updateCurriculum(String curriculumId, String instituteId,CurriculumRequest request);

    void deleteCurriculum(String instituteCurriculumId, String instituteId);

    List<MasterModules> getAllMasterModules(String name);

    List<MasterFormsEntity> getAllMasterForms(String formName);

    List<BranchCurriculumEntity> createBranchCurriculums(List<String> curriculumIds, String branchId);

    List<BranchCurriculumEntity> getBranchCurriculums(String branchId, String curriculumName);

    void deleteBranchCurriculum(String branchCurriculumId, String branchId);

    List<MasterAcademicYearEntity> getAllMasterAcademicYear(String name);

    List<MasterTermTypeEntity> getAllMasterTermTypes(String name);

    List<MasterDepartmentsEntity> getAllMasterDepartments(String name);

    List<MasterAcademicClassesEntity> getAllMasterAcademicClasses(String name);

    List<MasterRoleEntity> getAllMasterRoles(String name);

    List<MasterAcademicCurriculumEntity> getAllMasterAcademicCurriculum(String name);

    List<MasterTermEntity> getAllMasterTerm(String name);

}

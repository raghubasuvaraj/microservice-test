package com.sta.settings.service.impl;

import com.sta.dc.common.entity.academic.AcademicClassEntity;
import com.sta.dc.common.entity.academic.AcademicSectionEntity;
import com.sta.dc.common.entity.academic.BranchClassGroupEntity;
import com.sta.dc.common.repository.academic.AcademicClassRepository;
import com.sta.dc.common.repository.academic.BranchClassGroupRepository;
import com.sta.settings.dto.request.AcademicClassRequest;
import com.sta.settings.dto.request.ListClassGroupRequest;
import com.sta.settings.dto.response.ClassesResponse;
import com.sta.settings.dto.response.GroupWithClassResponse;
import com.sta.settings.dto.response.SectionWithClassResponse;
import com.sta.settings.dto.response.SectionsResponse;
import com.sta.settings.entities.*;
import com.sta.settings.exception.SettingsException;
import com.sta.settings.repository.BranchInfoRepository;
import com.sta.settings.service.IClassGroupService;
import com.sta.settings.utility.ErrorCodesConstants;
import com.sta.settings.utility.MessageConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@Transactional
public class ClassGroupServiceImpl implements IClassGroupService {

    @Autowired
    private BranchClassGroupRepository branchclassGroupRepository;

    @Autowired
    private BranchInfoRepository branchInfoRepository;

    @Autowired
    AcademicClassRepository academicClassRepository;

    public List<BranchClassGroupEntity> saveClassGroup(ListClassGroupRequest request, String branchId){
        try {
            BranchInfoEntity branch = branchInfoRepository.findByIdAndActive(branchId, true).orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.BRANCH_NOT_FOUND, HttpStatus.NOT_FOUND, null));
            List<String> groups = request.getClassGroups();

            List<BranchClassGroupEntity> listClassGroupObjs = branchclassGroupRepository.findByBranchId(branchId);
            listClassGroupObjs.forEach(listGroups->{
                groups.forEach(group->{
                    if(listGroups.getClassGroupName().equalsIgnoreCase(group)){
                        throw new SettingsException(ErrorCodesConstants.CONFLICT, MessageConstants.CURRICULUM_ALREADY_EXIST,
                                HttpStatus.CONFLICT, null);
                    }
                });
            });
            List<BranchClassGroupEntity> classGroupList = new ArrayList<>();

            groups.forEach(classGroups -> {
                BranchClassGroupEntity groupObj = new BranchClassGroupEntity();
                groupObj.setClassGroupName(classGroups);
                groupObj.setBranchId(branch.getId());
                classGroupList.add(groupObj);
            });

            return branchclassGroupRepository.saveAll(classGroupList);

        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.CLASS_GROUPS_CREATE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public  void addStandardsToClassGroup(AcademicClassRequest request,String groupId) {
        try {
            List<String> classIds = request.getClassIds();

            List<AcademicClassEntity> listClassObj = academicClassRepository.findByIdIn(classIds);

            BranchClassGroupEntity ClassGroupEntity = branchclassGroupRepository.findById(groupId)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.CLASS_GROUPS_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            listClassObj.stream().map(classObj -> {
                classObj.setClassGroups(ClassGroupEntity);
                return classObj;
            }).collect(Collectors.toList());
        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.STANDARDS_ADDING_TO_CLASS_GROUPS_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public void removeStandardsFromClassGroup(AcademicClassRequest request, String groupId) {
        try {
            List<String> classIds = request.getClassIds();

            List<AcademicClassEntity> listClassObj = academicClassRepository.findByIdIn(classIds);

            BranchClassGroupEntity ClassGroupEntity = branchclassGroupRepository.findById(groupId)
                    .orElseThrow(() -> new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.CLASS_GROUPS_NOT_FOUND,
                            HttpStatus.NOT_FOUND, null));

            listClassObj.stream().map(classObj -> {
                classObj.setClassGroups(null);
                return classObj;
            }).collect(Collectors.toList());
        } catch (SettingsException se) {
            throw se;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(ErrorCodesConstants.INTERNAL_SERVER_ERROR, MessageConstants.REMOVING_STANDARDS_FROM_CLASS_GROUP_FAILED, HttpStatus.INTERNAL_SERVER_ERROR, e);
        }
    }

    @Override
    public List<GroupWithClassResponse> getAllGroupsWithStandards() {
        try {
            List<GroupWithClassResponse> response = new ArrayList<>();
            log.info("Get all the academicClasses where active-true");
            List<BranchClassGroupEntity> branchClassesGroups = branchclassGroupRepository.findAllByActive(true);
            if (!CollectionUtils.isEmpty(branchClassesGroups)) {
                log.info("All the academicClasses listed");
                for (BranchClassGroupEntity bcEntity : branchClassesGroups) {
                    log.info("Find the sections assigned to each class");
                    List<AcademicClassEntity> classesEntities = academicClassRepository.findAllByClassGroupsIdAndActive(bcEntity.getId(), true);
                    if (!CollectionUtils.isEmpty(classesEntities)) {
                        List<ClassesResponse> classesResponse = classesEntities.stream().map(cEntity -> new ClassesResponse(cEntity.getId(), cEntity.getName(), cEntity.isActive())).toList();
                        log.info("Setting the response...");
                        response.add(new GroupWithClassResponse(bcEntity.getId(), bcEntity.getClassGroupName(), classesResponse));
                    }
                }
            } else {
                log.error("Classes can not found");
                throw new SettingsException(ErrorCodesConstants.NOT_FOUND, MessageConstants.CLASS_GROUPS_GET_ALL_FAILED, HttpStatus.NOT_FOUND, null);
            }
            return response;
        } catch (SettingsException e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new SettingsException(e.getErrorCode(), e.getMessage(), e.getStatusCode(), e);
        }
    }


}

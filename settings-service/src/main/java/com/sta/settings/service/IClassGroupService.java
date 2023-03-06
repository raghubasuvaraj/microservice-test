package com.sta.settings.service;

import com.sta.dc.common.entity.academic.BranchClassGroupEntity;
import com.sta.settings.dto.request.AcademicClassRequest;
import com.sta.settings.dto.request.ListClassGroupRequest;
import com.sta.settings.dto.response.GroupWithClassResponse;

import java.util.List;

public interface IClassGroupService {

    List<BranchClassGroupEntity> saveClassGroup(ListClassGroupRequest request, String branchId);

    void addStandardsToClassGroup(AcademicClassRequest request, String groupId);

    void removeStandardsFromClassGroup(AcademicClassRequest request, String groupId);

    List<GroupWithClassResponse> getAllGroupsWithStandards();
}

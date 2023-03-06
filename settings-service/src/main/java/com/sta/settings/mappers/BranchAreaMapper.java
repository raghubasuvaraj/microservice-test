package com.sta.settings.mappers;

import com.sta.settings.dto.request.BranchAreaRequest;
import com.sta.settings.dto.response.BranchAreaResponse;
import com.sta.settings.entities.BranchAreaEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BranchAreaMapper {
    public static BranchAreaEntity convertBranchAreaRequestToEntity(BranchAreaRequest branchAreaRequest) {

        return BranchAreaEntity.builder()
                .branchId(branchAreaRequest.getBranchId())
                .name(branchAreaRequest.getAreaName())
                .pinCode(branchAreaRequest.getPinCode())
                .build();
    }
    public static BranchAreaResponse convertBranchAreaEntityToResponse(BranchAreaEntity branchAreaEntity) {

        return BranchAreaResponse.builder()
                .id(branchAreaEntity.getId())
                .branchId(branchAreaEntity.getBranchId())
                .name(branchAreaEntity.getName())
                .pinCode(branchAreaEntity.getPinCode())
                .active(branchAreaEntity.isActive())
                .build();
    }
}

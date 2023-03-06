package com.sta.settings.mappers;

import com.sta.settings.dto.request.AreaRequest;
import com.sta.settings.dto.response.AreaResponse;
import com.sta.settings.entities.AreaEntity;

public class AreaMapper {

    public static AreaEntity convertAreaRequestToEntity(AreaRequest areaRequest){
        return AreaEntity.builder()
                .branchId(areaRequest.getBranchId())
                .areaName((areaRequest.getAreaName()))
                .areaPinCode(areaRequest.getPinCode())
                .build();
    }

    public static AreaResponse convertAreaEntityToResponse(AreaEntity areaEntity){
        return AreaResponse.builder()
                .id(areaEntity.getId())
                .branchId(areaEntity.getBranchId())
                .areaName(areaEntity.getAreaName())
                .pinCode(areaEntity.getAreaPinCode())
                .active(areaEntity.isActive())
                .build();
    }
}

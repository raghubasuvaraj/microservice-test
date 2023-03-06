package com.sta.settings.mappers;

import com.sta.settings.dto.request.MasterFieldRequest;
import com.sta.settings.dto.response.MasterFieldResponse;
import com.sta.settings.entities.MasterFieldEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MasterFieldMapper {
    public static MasterFieldEntity toMasterFieldEntity(MasterFieldRequest masterFieldDto) {

        return MasterFieldEntity.builder()
                .instituteId(masterFieldDto.getInstituteId())
                .configName(masterFieldDto.getConfigName())
                .configValue(masterFieldDto.getConfigValue())
                .build();
    }

    public static MasterFieldResponse convertEntityToDto(MasterFieldEntity masterFieldEntity) {

        return MasterFieldResponse.builder()
                .instituteId(masterFieldEntity.getInstituteId())
                .configName(masterFieldEntity.getConfigName())
                .configValue(masterFieldEntity.getConfigValue())
                .active(masterFieldEntity.isActive())
                .id(masterFieldEntity.getId())
                .modifiedBy(masterFieldEntity.getModifiedBy())
                .build();
    }


}

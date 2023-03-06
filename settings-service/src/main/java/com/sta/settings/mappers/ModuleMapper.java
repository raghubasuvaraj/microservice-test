package com.sta.settings.mappers;

import com.sta.settings.dto.ModuleDto;
import com.sta.settings.entities.ModuleEntity;

public interface ModuleMapper {
    default ModuleDto apply(ModuleEntity moduleEntity){
        return ModuleDto.builder()
                .id(moduleEntity.getId())
                .name(moduleEntity.getName())
                .isActive(moduleEntity.isActive())
                .build();
    }
}

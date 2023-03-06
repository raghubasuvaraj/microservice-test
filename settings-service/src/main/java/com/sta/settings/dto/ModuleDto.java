package com.sta.settings.dto;

import com.sta.dc.common.utility.SettingsConstants;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Request data transfer object(DTO) for module.
 *
 * @author shane
 */
@Data
@Builder
public class ModuleDto {
    private String id;

    @NotBlank(message = SettingsConstants.MANDATORY)
    private String name;

    @NotBlank(message = SettingsConstants.MANDATORY)
    private String code;

    @NotNull(message = SettingsConstants.MANDATORY)
    private boolean isMandatory;

    @NotNull(message = SettingsConstants.MANDATORY)
    private boolean isActive;
}

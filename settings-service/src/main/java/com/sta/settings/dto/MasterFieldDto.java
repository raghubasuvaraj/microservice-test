package com.sta.settings.dto;

import com.sta.dc.common.utility.SettingsConstants;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@Builder
public class MasterFieldDto {
    private String id;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String instituteId;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String configName;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private Set<String> configValues;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private Boolean status;
}

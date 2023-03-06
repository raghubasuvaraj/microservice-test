package com.sta.settings.dto.request;

import com.sta.dc.common.utility.SettingsConstants;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class MasterFieldRequest {

    private String id;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String instituteId;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String configName;
    private String configValue;
    private Boolean status;
}

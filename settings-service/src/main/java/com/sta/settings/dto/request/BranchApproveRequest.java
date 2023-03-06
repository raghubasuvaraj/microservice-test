package com.sta.settings.dto.request;

import com.sta.dc.common.utility.SettingsConstants;
import lombok.Data;

import javax.validation.constraints.NotBlank;
@Data
public class BranchApproveRequest {
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String id;

    @NotBlank(message = SettingsConstants.MANDATORY)
    private String approvedStatus;

    private String comments;
}

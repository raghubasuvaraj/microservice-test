package com.sta.settings.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sta.dc.common.utility.SettingsConstants;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class TermRequestForBranch {

    @NotNull(message = "termTypeId  is required.")
    private String instituteTermId;

    @NotBlank(message = SettingsConstants.MANDATORY)
    @JsonFormat(pattern="yyyy-mm-dd")
    private String startDate;

    @NotBlank(message = SettingsConstants.MANDATORY)
    @JsonFormat(pattern="yyyy-mm-dd")
    private String endDate;

}

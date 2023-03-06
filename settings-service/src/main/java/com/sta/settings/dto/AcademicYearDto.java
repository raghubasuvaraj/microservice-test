package com.sta.settings.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sta.dc.common.utility.SettingsConstants;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
public class AcademicYearDto {

    private String id;

    @NotNull(message = SettingsConstants.MANDATORY)
    @Pattern(regexp = "^[0-9]{4}[-][0-9]{2}$", message = "Year Name can only be YYYY-YY format")
    private String name;
    @NotBlank(message = SettingsConstants.MANDATORY)
    @JsonFormat(pattern="yyyy-mm-dd")
    private String startDate;
    @NotBlank(message = SettingsConstants.MANDATORY)
    @JsonFormat(pattern="yyyy-mm-dd")
    private String endDate;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String instituteId;
}

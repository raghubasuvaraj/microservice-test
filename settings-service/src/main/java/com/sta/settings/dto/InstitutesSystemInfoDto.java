package com.sta.settings.dto;

import com.sta.dc.common.utility.SettingsConstants;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class InstitutesSystemInfoDto {
    private String id;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String instituteId;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String country;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String timeZone;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String defaultLanguage;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String dateFormat;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String timeFormat;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String floatPrecision;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String numberFormat;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String firstWeekDay;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String currencyPrecision;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String sessionExpiry;
}

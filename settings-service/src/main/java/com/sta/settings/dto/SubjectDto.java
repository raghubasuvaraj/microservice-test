package com.sta.settings.dto;

import com.sta.dc.common.utility.SettingsConstants;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Request and Response data transfer object(DTO) for curriculum subject.
 *
 * @author shane
 */
@Data
@Builder
public class SubjectDto {

    private String id;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String curriculumId;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String branchId;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String subjectName;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String shortName;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String coScholasticType;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String subjectType;
}

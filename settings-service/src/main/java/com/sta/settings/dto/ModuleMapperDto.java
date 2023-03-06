package com.sta.settings.dto;

import com.sta.dc.common.utility.SettingsConstants;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Request data transfer object(DTO) for branch module mapping.
 *
 * @author shane
 */
@Data
@Builder
public class ModuleMapperDto {
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String branchId;

    @NotNull(message = SettingsConstants.MANDATORY)
    private List<String> modulesList;

}

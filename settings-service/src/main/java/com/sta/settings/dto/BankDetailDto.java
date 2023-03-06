package com.sta.settings.dto;

import com.sta.dc.common.utility.SettingsConstants;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Request and Response data transfer object(DTO) for bank detail.
 *
 * @author shane
 */
@Data
@Builder
public class BankDetailDto {

    private String id;

    @NotBlank(message = SettingsConstants.MANDATORY)
    private String branchId;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String bankAccountNumber;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String bankAccountName;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String bankName;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String ifscCode;
    @NotBlank(message = SettingsConstants.MANDATORY)
    private String bankAccountBranch;
    private boolean isPrimary;
}

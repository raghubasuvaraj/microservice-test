package com.sta.settings.entities;

import com.sta.dc.common.utility.SettingsConstants;
import com.sta.settings.enums.CommonStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Entity
@Table(name = "mse_branch_types")
public class MasterBranchTypeEntity extends BaseEntity {

    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "name", unique = true)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;

}

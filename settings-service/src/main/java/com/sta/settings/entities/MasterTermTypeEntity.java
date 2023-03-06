package com.sta.settings.entities;

import com.sta.settings.enums.CommonStatus;
import com.sta.dc.common.utility.SettingsConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mse_master_term_types")
public class MasterTermTypeEntity extends BaseEntity {

    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "name", unique = true)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;
}

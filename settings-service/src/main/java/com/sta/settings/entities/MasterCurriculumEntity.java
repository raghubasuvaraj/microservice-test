package com.sta.settings.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "mse_master_academic_curriculum")
public class MasterCurriculumEntity extends BaseEntity {

    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "name", unique = true)
    private String name;

    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "short_code",unique = true)
    private String code;

    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;

}

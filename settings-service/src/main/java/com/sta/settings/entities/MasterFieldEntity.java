package com.sta.settings.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.dc.common.utility.SettingsConstants;
import com.sta.settings.enums.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "mse_config_fields")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MasterFieldEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -3879025180544377799L;

    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "institute_id")
    private String instituteId;
    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "config_name")
    private String configName;
    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "config_value")
    private String configValue;
    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;
}

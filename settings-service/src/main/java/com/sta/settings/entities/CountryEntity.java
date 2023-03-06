package com.sta.settings.entities;

import com.sta.dc.common.utility.SettingsConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Entity
@Table(name = "m_country")
public class CountryEntity extends BaseEntity {

    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "code", unique = true)
    private String code;

    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "val",unique = true)
    private String value;

    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "reference")
    private String reference;

    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "ref_key", unique = true)
    private String referenceKey;
}

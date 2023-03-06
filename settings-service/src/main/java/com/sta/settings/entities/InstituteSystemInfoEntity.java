package com.sta.settings.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.dc.common.utility.SettingsConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "mse_institute_system_info")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstituteSystemInfoEntity extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = -3879025180544377799L;

    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "institute_id", unique = true)
    private String instituteId;
    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "country", unique = true)
    private String country;
    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "timezone", unique = true)
    private String timeZone;
    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "default_language", unique = true)
    private String defaultLanguage;
    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "date_format", unique = true)
    private String dateFormat;
    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "time_format", unique = true)
    private String timeFormat;
    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "float_precision", unique = true)
    private String floatPrecision;
    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "number_format", unique = true)
    private String numberFormat;
    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "first_week_day", unique = true)
    private String firstWeekDay;
    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "currency_precision", unique = true)
    private String currencyPrecision;
    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "session_expiry", unique = true)
    private String sessionExpiry;
	@Column(name="is_active")
	private boolean active= true;

}

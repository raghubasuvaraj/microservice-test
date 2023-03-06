package com.sta.settings.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.sta.settings.enums.CommonStatus;
import org.hibernate.annotations.DynamicInsert;

import com.sta.settings.enums.ApprovedStatus;
import com.sta.dc.common.utility.SettingsConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Persistence layer for Master-forms
 * 
 * @author r@ghu
 *
 */
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "mse_master_forms")
@DynamicInsert
public class MasterFormsEntity extends BaseEntity {

	@NotBlank(message = SettingsConstants.MANDATORY)
	@Column(name = "name", unique = true)
	private String name;

	@NotBlank(message = SettingsConstants.MANDATORY)
	@Column(name = "short_code", unique = true)
	private String shortCode;

	@Enumerated(EnumType.ORDINAL)
	private CommonStatus status;

}

package com.sta.settings.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import com.sta.settings.enums.CommonStatus;
import com.sta.dc.common.utility.SettingsConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This entity design done by the DB.
 * For more please look in below session
 * <p>
 * Host: 13.233.254.229
 * @author r@ghu
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "mse_master_institute_group")
public class MasterInstituteGroups extends BaseEntity {
	
	@NotBlank(message = SettingsConstants.MANDATORY)
	@Column(name = "name", length = 50)
	private String instituteGroupName;
	
	@NotBlank(message = SettingsConstants.MANDATORY)
	@Column(name = "affliation_no", length = 50)
	private String affliationNo;

	@Enumerated(EnumType.ORDINAL)
	private CommonStatus status;

}

package com.sta.settings.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.dc.common.utility.SettingsConstants;
import com.sta.settings.enums.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
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
@Table(name = "mse_branch_forms")
@JsonIgnoreProperties({"branchInfoEntity"})
public class BranchFormsEntity extends BaseEntity {
	
	@ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    private BranchInfoEntity branchInfoEntity;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "form_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer"})
	private MasterFormsEntity masterFormsEntity;
	
	@NotBlank(message = SettingsConstants.MANDATORY)
	@Column(name = "form_url")
	private String formUrl;

	@Enumerated(EnumType.ORDINAL)
	private CommonStatus status;

}

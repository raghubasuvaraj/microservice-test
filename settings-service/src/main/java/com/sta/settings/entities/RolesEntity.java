package com.sta.settings.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.settings.enums.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Persistence layer for role
 * 
 * @author r@ghu
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
@Entity
@Table(name = "mse_institute_roles")
@JsonIgnoreProperties({"createdAt","createdBy","lastModifiedAt","deletedAt","modifiedBy","institutesEntity"})
public class RolesEntity extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "role_id")
	private MasterRoleEntity role;

	@ManyToOne
	@JoinColumn(name = "institute_id")
	private InstitutesEntity institutesEntity;

	@Enumerated(EnumType.ORDINAL)
	private CommonStatus status;
}
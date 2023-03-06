package com.sta.settings.entities;

import java.io.Serial;
import java.io.Serializable;

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

/**
 * Persistence layer for departments
 * <p>
 * Keeping the department+short_code+branch_id as composite key.
 * 
 * @author r@ghu | Thur 28th July 2022
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "mse_institute_departments")
@JsonIgnoreProperties({"institute","hibernateLazyInitializer"})
public class InstituteDepartmentsEntity extends BaseEntity implements Serializable {

	@Serial
	private static final long serialVersionUID = 1657904585815313094L;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "institute_id")
	private InstitutesEntity institute;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "department_id")
	private MasterDepartmentsEntity masterDepartment;

	@Enumerated(EnumType.ORDINAL)
	private CommonStatus status;

}

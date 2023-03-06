package com.sta.settings.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.sta.dc.common.entity.academic.BranchClassGroupEntity;
import com.sta.dc.common.utility.SettingsConstants;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Persistence layer for Sections.
 * <p>
 * class: FK not making as mandatory. (Will change it after the confirmation)
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
@Table(name = "sections")
public class SectionEntity extends BaseEntity {
	
	@Column(name = "name")
	@NotBlank(message = SettingsConstants.MANDATORY)
	private String name;
	
	@ManyToOne
    @JoinColumn (name = "class_id")
    private BranchClassGroupEntity classGroupEntity;
	
	@ManyToOne
	@NotNull(message = SettingsConstants.MANDATORY)
    @JoinColumn (name = "branch_id")
    private BranchInfoEntity branchInfoEntity;
	
	@Column(name="active")
	private boolean active= true;

}

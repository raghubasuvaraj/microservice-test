package com.sta.settings.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.settings.enums.CommonStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mse_academic_curriculum")
@JsonIgnoreProperties({"branchInfoEntity"})
public class BranchCurriculumEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curriculum_id")
    private InstituteCurriculumEntity instituteCurriculum;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    private BranchInfoEntity branchInfoEntity;

    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;
}

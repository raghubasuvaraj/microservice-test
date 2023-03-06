package com.sta.settings.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.settings.enums.CommonStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mse_institute_academic_curriculum")
@JsonIgnoreProperties({"institute"})
public class InstituteCurriculumEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "institute_id")
    private InstitutesEntity institute;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curriculum_id")
    private MasterCurriculumEntity masterCurriculum;

    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;
}

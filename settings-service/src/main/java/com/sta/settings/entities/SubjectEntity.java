package com.sta.settings.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.settings.enums.CommonStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mse_academic_subjects")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubjectEntity extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 3307953998559259491L;

    @Column(name = "curriculum_id")
    private String curriculumId;
    @Column(name = "branch_id")
    private String branchId;
    @Column(name = "name")
    private String subjectName;
    @Column(name = "short_name")
    private String shortName;
    @Column(name = "coscholastic_type")
    private String coScholasticType;
    @Column(name = "type")
    private String subjectType;
    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;
}

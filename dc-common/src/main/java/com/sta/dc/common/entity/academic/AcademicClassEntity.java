package com.sta.dc.common.entity.academic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.dc.common.entity.base.BaseEntity;
import com.sta.dc.common.enums.CommonStatus;
import com.sta.dc.common.entity.directory.UserManagementEmployeeDetailsEntity;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
@Table(name = "mse_academic_classes")
public class AcademicClassEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "mse_academic_curriculum_id")
    private AcademicCurriculumEntity academicCurriculum;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "mse_institute_academic_classes_id")
    private InstituteAcademicClassesEntity classesEntity;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;

    @Column(name = "position")
    private Integer position;

    @Column(name = "is_hostel_facility")
    private Boolean isHostelFacility;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "primary_class_teacher_id")
    private UserManagementEmployeeDetailsEntity primaryClassTeacher;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "secondary_class_teacher_id")
    private UserManagementEmployeeDetailsEntity secondaryClassTeacher;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "academic_year_id")
    private AcademicYearEntity academicYear;


    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "mse_academic_class_groups_id")
    private BranchClassGroupEntity classGroups;

}

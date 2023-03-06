package com.sta.dc.common.entity.academic;

import com.sta.dc.common.entity.base.BaseEntity;
import com.sta.dc.common.entity.directory.StudentDetailEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "mse_admissison_student_academic_year")
public class SectionStudentMappingEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "mse_admission_student_details_id")
    private StudentDetailEntity studentDetailEntity;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "mse_academic_year_id")
    private AcademicYearEntity academicYear;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "mse_academic_classes_id")
    private AcademicClassEntity academicClassEntity;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "mse_academic_sections_id")
    private AcademicSectionEntity academicSectionEntity;

    @Column(name = "roll_no")
    private String roll;
}

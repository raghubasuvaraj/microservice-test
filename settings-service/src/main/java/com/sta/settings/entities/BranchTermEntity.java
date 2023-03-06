package com.sta.settings.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.settings.enums.CommonStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@JsonIgnoreProperties({"branch"})
@Table(name = "mse_academic_term")
public class BranchTermEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "branch_id")
    private BranchInfoEntity branch;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "academic_year_id")
    private InstituteAcademicYearEntity academicYear;

    @ManyToOne
    @JoinColumn(name = "term_id")
    private InstituteTermEntity instituteTerm;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;
}

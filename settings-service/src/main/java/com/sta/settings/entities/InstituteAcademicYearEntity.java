package com.sta.settings.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.settings.enums.CommonStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mse_institute_academic_year")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InstituteAcademicYearEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 5922981764350471465L;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "year_id", insertable = false, updatable = false)
    private MasterAcademicYearEntity masterAcademicYearEntity;
    @Column(name = "year_id")
    private String yearId;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    @Column(name = "institute_id", unique = false)
    private String instituteId;
    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;

}

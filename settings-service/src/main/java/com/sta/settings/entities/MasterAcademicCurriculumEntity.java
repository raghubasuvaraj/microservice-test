package com.sta.settings.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mse_master_academic_curriculum")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MasterAcademicCurriculumEntity extends BaseEntity{
    @Column(name = "name")
    private String name;

    @Column(name = "short_code")
    private String shortCode;

    @Column(name = "status")
    private Integer status;
}

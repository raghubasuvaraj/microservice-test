package com.sta.dc.common.entity.academic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.dc.common.entity.base.BaseEntity;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "mse_institute_academic_classes")
public class InstituteAcademicClassesEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "class_id")
    private MasterAcademicClassesEntity masterClassesEntity;

}

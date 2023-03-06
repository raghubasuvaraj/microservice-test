package com.sta.dc.common.entity.academic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.dc.common.entity.base.BaseEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mse_master_academic_classes")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MasterAcademicClassesEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "short_code")
    private String shortCode;

    @Column(name = "position")
    private Integer position;
}

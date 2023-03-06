package com.sta.settings.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mse_master_departments")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MasterDepartmentsEntity extends BaseEntity implements Serializable {

    @Column(name = "name")
    private String name;

    @Column(name = "short_code")
    private String shortCode;
}

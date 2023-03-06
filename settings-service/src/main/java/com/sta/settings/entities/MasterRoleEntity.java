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
@Table(name = "mse_master_roles")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MasterRoleEntity extends BaseEntity{

    @Column(name = "name")
    private String name;
}

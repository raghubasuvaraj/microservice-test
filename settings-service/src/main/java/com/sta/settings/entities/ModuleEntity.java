package com.sta.settings.entities;

import com.sta.dc.common.utility.SettingsConstants;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mse_master_modules")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModuleEntity extends BaseEntity implements Serializable {

    @Serial
    private static final long serialVersionUID = 6266849483114760897L;

    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "name", unique = true)
    private String name;

}

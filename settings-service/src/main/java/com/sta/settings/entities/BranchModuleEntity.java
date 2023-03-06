package com.sta.settings.entities;

import com.sta.dc.common.utility.SettingsConstants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mse_branch_modules")
public class BranchModuleEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -8344723062677729509L;

    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "branch_id")
    private String branchId;

    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "module_id")
    private String moduleId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "module_id", insertable = false, updatable = false)
    private ModuleEntity moduleEntity;

    @Column(name = "status", columnDefinition = "boolean default false")
    private String status;

}

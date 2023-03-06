package com.sta.dc.common.entity.academic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.dc.common.entity.base.BaseEntity;
import com.sta.dc.common.enums.CommonStatus;
import com.sta.dc.common.utility.SettingsConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Entity
@Table(name = "mse_academic_class_groups")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"branch"})
public class BranchClassGroupEntity extends BaseEntity implements Serializable {

    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "name")
    private String classGroupName;


    @Column(name = "branch_id")
    private String branchId;

    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;


}

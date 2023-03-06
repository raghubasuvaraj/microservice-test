package com.sta.settings.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.settings.enums.CommonStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serial;
import java.io.Serializable;

@Data
@Entity
@Table(name = "mse_branch_areas")
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BranchAreaEntity extends BaseEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = -3879025182544399799L;

    @Column(name = "branch_id")
    private String branchId;

    @Column(name = "name")
    private String name;

    @Column(name = "pincode")
    private String pinCode;

    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;
}

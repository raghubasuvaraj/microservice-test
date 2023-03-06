package com.sta.settings.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.settings.enums.CommonStatus;
import com.sta.dc.common.utility.SettingsConstants;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@JsonIgnoreProperties({"institute"})
@Table(name = "mse_institute_terms")
public class InstituteTermEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "term_id")
    private MasterTermEntity masterTermEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "term_type_id")
    private MasterTermTypeEntity termType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "institute_id")
    private InstitutesEntity institute;

    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;

}

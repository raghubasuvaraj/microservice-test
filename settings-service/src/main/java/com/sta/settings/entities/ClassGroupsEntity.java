package com.sta.settings.entities;

import com.sta.settings.enums.CommonStatus;
import com.sta.dc.common.utility.SettingsConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
@Entity
@Table(name = "m_class_groups")
public class ClassGroupsEntity extends BaseEntity {

    @NotBlank(message = SettingsConstants.MANDATORY)
    @Column(name = "name", unique = true)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;

    @Column(name="active")
    private boolean active= true;
}

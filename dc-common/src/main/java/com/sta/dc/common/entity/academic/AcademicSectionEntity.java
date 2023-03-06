package com.sta.dc.common.entity.academic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.dc.common.entity.base.BaseEntity;
import com.sta.dc.common.enums.CommonStatus;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

@Entity
@Table(name = "mse_academic_sections")
public class AcademicSectionEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "msc_academic_class_id")
    private AcademicClassEntity academicClass;

    @Column(name = "name")
    private String name;

    @Column(name = "short_code")
    private String shortCode;

    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "mse_branch_building_room_blocks_id")
    private BranchBuildingRoomBlockEntity roomBlockEntity;

    @Column(name = "student_count")
    private Integer studentCount;

}

package com.sta.settings.entities;

import com.sta.settings.enums.CommonStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mse_master_fields_data")
public class MasterFieldsDataEntity extends BaseEntity {

    @Column(name = "display_name1")
    private String name1;

    @Column(name = "display_name2")
    private String name2;

    @Column(name = "display_name3")
    private String name3;

    @Column(name = "short_code", unique = true)
    private String shortCode;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "field_ref_id")
    private String fieldReferenceId;

    @Enumerated(EnumType.ORDINAL)
    private CommonStatus status;


}

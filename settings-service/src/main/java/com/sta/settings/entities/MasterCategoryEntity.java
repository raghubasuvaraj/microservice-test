package com.sta.settings.entities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@ToString
@Entity
@Table(name = "mse_master_category_list")
public class MasterCategoryEntity extends BaseEntity {

    @Column(name="name")
    private String name;
}

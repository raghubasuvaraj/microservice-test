package com.sta.dc.common.entity.academic;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.dc.common.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "mse_branch_building_room_blocks")
public class BranchBuildingRoomBlockEntity extends BaseEntity {

}

package com.sta.dc.common.entity.directory;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sta.dc.common.entity.base.BaseEntity;

import javax.persistence.Entity;
import javax.persistence.Table;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "mse_usermgnt_employee_details")
public class UserManagementEmployeeDetailsEntity extends BaseEntity {

}

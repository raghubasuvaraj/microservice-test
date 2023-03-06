package com.sta.settings.entities;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@ToString
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"createdAt","createdBy","lastModifiedAt","deletedAt","modifiedBy"})
public abstract class BaseEntity {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(length = 50)
	protected String id;

	@CreatedDate
	@Column(name = "created_date")
	private LocalDateTime createdAt;

	@Column(name = "created_by")
	private String createdBy;

	@LastModifiedDate
	@Column(name = "modified_date")
	private LocalDateTime lastModifiedAt;

	@Column(name ="deleted_date")
	private LocalDateTime deletedAt;

	@Column(name = "modified_by")
	private String modifiedBy;

	@Column(name="is_active")
	private boolean active= true;

}
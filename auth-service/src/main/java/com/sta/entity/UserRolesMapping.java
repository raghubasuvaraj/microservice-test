package com.sta.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * This persistence layer is only for demo purpose once the demo content is
 * confirmed this layer will be terminate.
 * <p>
 * The @Id should not be in this entity layer it must be in an abstracter class,
 * because @Id a common column in each entities. This will remove after the demo.
 * 
 * @author r@ghu
 *
 */
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_roles_mapping", uniqueConstraints = { @UniqueConstraint(columnNames = { "users_id", "roles_id" }) })
public class UserRolesMapping {

	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(columnDefinition = "CHAR(32)", unique = true)
	private String id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "users_id")
	private Users users;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "roles_id")
	private Roles roles;
}

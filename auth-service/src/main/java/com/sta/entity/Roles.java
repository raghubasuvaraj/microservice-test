package com.sta.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Setter;
import lombok.ToString;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "roles")
public class Roles {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	@Column(columnDefinition = "CHAR(32)", unique = true)
	private String id;
	
	@Column(name = "role", columnDefinition = "CHAR(32)", unique = true)
	private String role;
}
